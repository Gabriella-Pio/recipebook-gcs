import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { RecipeService } from '../../services/recipe.service';
import { Recipe } from '../../models/recipe.model';

@Component({
  selector: 'app-recipe-list',
  standalone: true,
  imports: [CommonModule, FormsModule, MatIconModule],
  templateUrl: './recipe-list.component.html',
  styleUrl: './recipe-list.component.css',
})
export class RecipeListComponent implements OnInit {
  receitas: Recipe[] = [];
  termoBusca: string = '';

  constructor(private recipeService: RecipeService) {}

  ngOnInit(): void {
    this.recipeService.listar().subscribe({
      next: (dados) => {
        this.receitas = dados;
      },
      error: (err) => {
        console.error('Erro ao carregar receitas:', err);
      },
    });
  }

  get receitasFiltradas(): Recipe[] {
    if (!this.termoBusca || !this.termoBusca.trim()) {
      return this.receitas;
    }

    const termoFormatado = this.termoBusca.toLowerCase().trim();

    return this.receitas.filter((receita) =>
      receita.nome.toLowerCase().includes(termoFormatado)
    );
  }
}
