package com.recipebook.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RECEITA")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome da receita é obrigatório")
  @Size(min = 3, message = "O nome da receita deve conter no minimo 3 caracteres")
  @Column(nullable = false, unique = true)
  private String nome;

  @NotNull(message = "A categoria é obrigatória")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Categoria categoria;

  @NotNull(message = "O tempo de preparo é obrigatório")
  @Min(value = 1, message = "O tempo de preparo deve ser no mínimo 1 minuto")
  @Column(nullable = false)
  private Integer tempoPreparo;

  @NotNull(message = "O número de porções é obrigatório")
  @Min(value = 1, message = "O número de porções deve ser no mínimo 1")
  @Column(nullable = false)
  private Integer porcoes;

  @NotEmpty(message = "A lista de ingredientes não pode estar vazia")
  @ElementCollection // Para armazenar uma lista de strings
  @CollectionTable(name = "TB_RECEITA_INGREDIENTES", joinColumns = @JoinColumn(name = "receita_id"))
  @Column(name = "ingrediente", nullable = false)
  private List<String> ingredientes;

  @NotBlank(message = "O modo de preparo é obrigatório")
  @Size(min = 10, message = "O modo de preparo deve conter no mínimo 10 caracteres")
  @Lob // Para armazenar textos longos
  @Column(nullable = false, columnDefinition = "TEXT")
  private String modoPreparo;

  @Column(nullable = false)
  private LocalDateTime dataCadastro;

  // Preenche a data de cadastro automaticamente antes de persistir
  @PrePersist
  protected void onCreate() {
    this.dataCadastro = LocalDateTime.now();
  }

  public Recipe() {
  }

  public Recipe(Long id, String nome, Categoria categoria, Integer tempoPreparo, Integer porcoes,
      List<String> ingredientes, String modoPreparo, LocalDateTime dataCadastro) {
    this.id = id;
    this.nome = nome;
    this.categoria = categoria;
    this.tempoPreparo = tempoPreparo;
    this.porcoes = porcoes;
    this.ingredientes = ingredientes;
    this.modoPreparo = modoPreparo;
    this.dataCadastro = dataCadastro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Integer getTempoPreparo() {
    return tempoPreparo;
  }

  public void setTempoPreparo(Integer tempoPreparo) {
    this.tempoPreparo = tempoPreparo;
  }

  public Integer getPorcoes() {
    return porcoes;
  }

  public void setPorcoes(Integer porcoes) {
    this.porcoes = porcoes;
  }

  public List<String> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(List<String> ingredientes) {
    this.ingredientes = ingredientes;
  }

  public String getModoPreparo() {
    return modoPreparo;
  }

  public void setModoPreparo(String modoPreparo) {
    this.modoPreparo = modoPreparo;
  }

  public LocalDateTime getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(LocalDateTime dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
    result = prime * result + ((tempoPreparo == null) ? 0 : tempoPreparo.hashCode());
    result = prime * result + ((porcoes == null) ? 0 : porcoes.hashCode());
    result = prime * result + ((ingredientes == null) ? 0 : ingredientes.hashCode());
    result = prime * result + ((modoPreparo == null) ? 0 : modoPreparo.hashCode());
    result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Recipe other = (Recipe) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    if (categoria != other.categoria)
      return false;
    if (tempoPreparo == null) {
      if (other.tempoPreparo != null)
        return false;
    } else if (!tempoPreparo.equals(other.tempoPreparo))
      return false;
    if (porcoes == null) {
      if (other.porcoes != null)
        return false;
    } else if (!porcoes.equals(other.porcoes))
      return false;
    if (ingredientes == null) {
      if (other.ingredientes != null)
        return false;
    } else if (!ingredientes.equals(other.ingredientes))
      return false;
    if (modoPreparo == null) {
      if (other.modoPreparo != null)
        return false;
    } else if (!modoPreparo.equals(other.modoPreparo))
      return false;
    if (dataCadastro == null) {
      if (other.dataCadastro != null)
        return false;
    } else if (!dataCadastro.equals(other.dataCadastro))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", tempoPreparo=" + tempoPreparo
        + ", porcoes=" + porcoes + ", ingredientes=" + ingredientes + ", modoPreparo=" + modoPreparo + ", dataCadastro="
        + dataCadastro + "]";
  }
}