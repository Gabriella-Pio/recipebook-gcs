package com.recipebook.controller;

import com.recipebook.entity.Recipe;
import com.recipebook.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class RecipeController {

  private final RecipeRepository recipeRepository;

  public RecipeController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @GetMapping
  public List<Recipe> listarTodas(@RequestParam(required = false) String nome) {
    if (nome != null && !nome.trim().isEmpty()) {
      return recipeRepository.findByNomeContainingIgnoreCaseOrderByDataCadastroDesc(nome);
    }
    return recipeRepository.findAllByOrderByDataCadastroDesc();
  }
}