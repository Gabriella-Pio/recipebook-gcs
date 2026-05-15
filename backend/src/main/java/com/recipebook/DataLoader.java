package com.recipebook;

import com.recipebook.entity.Categoria;
import com.recipebook.entity.Recipe;
import com.recipebook.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

  private final RecipeRepository recipeRepository;

  public DataLoader(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (recipeRepository.count() == 0) {

      Recipe r1 = new Recipe(null, "Brigadeiro Gourmet", Categoria.DOCE, 30, 20,
          Arrays.asList("1 lata de leite condensado", "1 colher de sopa de manteiga",
              "3 colheres de sopa de chocolate em pó", "Chocolate granulado"),
          "Em uma panela, misture o leite condensado, a manteiga e o chocolate em pó. Mexa em fogo médio até desgrudar do fundo. Deixe esfriar e faça bolinhas. Passe no granulado.",
          null);

      Recipe r2 = new Recipe(null, "Coxinha de Frango", Categoria.SALGADO, 60, 15,
          Arrays.asList("Peito de frango desfiado", "Farinha de trigo", "Caldo de galinha", "Tempero verde"),
          "Cozinhe o frango e desfie. Faça a massa utilizando o caldo do cozimento, recheie com o frango, modele em formato de coxinha, empaque e frite em óleo quente.",
          null);

      Recipe r3 = new Recipe(null, "Suco de Limão Natural", Categoria.BEBIDA, 5, 4,
          Arrays.asList("3 limões taiti", "1 litro de água filtrada", "Açúcar a gosto", "Pedras de gelo"),
          "Lave bem os limões, descasque e retire a parte central branca para não amargar. Bata no liquidificador com a água e o açúcar. Coe e sirva imediatamente com bastante gelo.", null);

      Recipe r4 = new Recipe(null, "Sorvete de Limão", Categoria.SOBREMESA, 5, 4,
          Arrays.asList("3 limões taiti", "1 litro de água filtrada", "Açúcar a gosto", "Pedras de gelo"),
          "Lave bem os limões, descasque e retire a parte central branca para não amargar. Bata no liquidificador com a água e o açúcar. Coe e leve ao congelador por 4 horas, mexendo a cada 30 minutos para evitar cristais de gelo.",
          null);

      recipeRepository.saveAll(Arrays.asList(r1, r2, r3, r4));

      System.out.println("\n>>> [GCS BASELINE]: 4 Receitas Iniciais Cadastradas com Sucesso! <<<\n");
    }
  }
}