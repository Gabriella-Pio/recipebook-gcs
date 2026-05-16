import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { RecipeService } from '../../services/recipe.service';
import { Recipe } from '../../models/recipe.model';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [CommonModule, MatIconModule, RouterLink, MatSnackBarModule],
  templateUrl: './recipe-detail.component.html',
  styleUrl: './recipe-detail.component.css',
})
export class RecipeDetailComponent implements OnInit {
  recipe: Recipe | null = null;
  loading = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private recipeService: RecipeService,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carregarReceita(Number(id));
    } else {
      this.error = 'ID da receita não encontrado';
      this.loading = false;
    }
  }

  carregarReceita(id: number): void {
    this.recipeService.buscarPorId(id).subscribe({
      next: (data) => {
        this.recipe = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erro ao carregar receita:', err);
        this.error = 'Receita não encontrada';
        this.loading = false;
      },
    });
  }

  confirmarExclusao(): void {
    if (!this.recipe || !this.recipe.id) return;

    const confirmou = confirm(
      `Tem certeza que deseja excluir a receita "${this.recipe.nome}"?`,
    );

    if (confirmou) {
      this.recipeService.excluir(this.recipe.id).subscribe({
        next: () => {
          this.snackBar.open('Receita excluída com sucesso!', 'Fechar', {
            duration: 5000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
            panelClass: ['success-snackbar'],
          });
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Erro ao excluir receita:', err);
          this.snackBar.open('Erro ao excluir receita.', 'Fechar', {
            duration: 5000,
          });
        },
      });
    }
  }

  voltar(): void {
    this.router.navigate(['/']);
  }
}
