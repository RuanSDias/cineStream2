package br.com.fiap.cineStream.model;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import br.com.fiap.cineStream.controller.FilmeController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    private Calendar dataDeLancamento;
    private String resumo;
    @ManyToMany(mappedBy = "filmes")
    private List<Categoria> categorias;

    public EntityModel<Filme> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(FilmeController.class).show(id)).withSelfRel(),
            linkTo(methodOn(FilmeController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(FilmeController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(FilmeController.class).show(this.getCategorias().get(1))).withRel("conta")
        );
    }
    
}
