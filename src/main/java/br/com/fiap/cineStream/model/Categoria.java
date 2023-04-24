package br.com.fiap.cineStream.model;

import java.util.List;

import br.com.fiap.cineStream.controller.FilmeController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToMany
    @JoinTable(name = "categorias_filmes", 
                joinColumns = @JoinColumn(name = "categoria_fk"), 
                inverseJoinColumns = @JoinColumn(name = "filme_fk"))
    private List<Filme> filmes;
}
