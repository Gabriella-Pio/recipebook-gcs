import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { RecipeService } from '../../services/recipe.service';
import { Recipe } from '../../models/recipe.model';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [CommonModule, MatIconModule, RouterLink],
  templateUrl: './recipe-detail.component.html',
  styleUrl: './recipe-detail.component.css'
})
export class RecipeDetailComponent implements OnInit {
  recipe: Recipe | null = null;
  loading = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private recipeService: RecipeService
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
      }
    });
  }

  voltar(): void {
    this.router.navigate(['/']);
  }
}
