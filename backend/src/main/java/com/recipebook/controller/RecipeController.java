package com.recipebook.controller;

import com.recipebook.entity.Recipe;
import com.recipebook.repository.RecipeRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @PostMapping
  public ResponseEntity<Recipe> salvar(@Valid @RequestBody Recipe recipe) {
    Recipe novaReceita = recipeRepository.save(recipe);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> buscarPorId(@PathVariable Long id) {
    return recipeRepository.findById(id)
        .map(recipe -> ResponseEntity.ok().body(recipe))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirReceita(@PathVariable Long id) {
    return recipeRepository.findById(id)
        .map(recipe -> {
          recipeRepository.delete(recipe);
          return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
  }
}