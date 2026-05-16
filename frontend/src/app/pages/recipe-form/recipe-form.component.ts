import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { RecipeService } from '../../services/recipe.service';
import { Categoria } from '../../models/recipe.model';

@Component({
  selector: 'app-recipe-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatIconModule, RouterLink],
  templateUrl: './recipe-form.component.html',
  styleUrl: './recipe-form.component.css'
})
export class RecipeFormComponent implements OnInit {
  form!: FormGroup;
  categorias: Categoria[] = ['DOCE', 'SALGADO', 'BEBIDA', 'SOBREMESA'];
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      categoria: ['', Validators.required],
      tempoPreparo: ['', [Validators.required, Validators.min(1)]],
      porcoes: ['', [Validators.required, Validators.min(1)]],
      ingredientes: this.fb.array([this.createIngredienteControl()]),
      modoPreparo: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  get ingredientes(): FormArray {
    return this.form.get('ingredientes') as FormArray;
  }

  createIngredienteControl(): FormGroup {
    return this.fb.group({
      ingrediente: ['', Validators.required]
    });
  }

  addIngrediente(): void {
    this.ingredientes.push(this.createIngredienteControl());
  }

  removeIngrediente(index: number): void {
    if (this.ingredientes.length > 1) {
      this.ingredientes.removeAt(index);
    }
  }

  onSubmit(): void {
    if (this.form.invalid || this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;

    // Transformar ingredientes do formato [{ ingrediente: 'x' }] para ['x']
    const formValue = this.form.value;
    const ingredientesArray = formValue.ingredientes.map((item: any) => item.ingrediente);

    const recipe = {
      ...formValue,
      ingredientes: ingredientesArray
    };

    this.recipeService.salvar(recipe).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Erro ao salvar receita:', error);
        this.isSubmitting = false;
      }
    });
  }
}
