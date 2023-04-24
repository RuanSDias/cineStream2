package br.com.fiap.cineStream.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cineStream.exception.RestNotFoundException;
import br.com.fiap.cineStream.model.Categoria;
import br.com.fiap.cineStream.model.Filme;
import br.com.fiap.cineStream.repository.CategoriaRepository;
import br.com.fiap.cineStream.repository.FilmeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FilmeRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    Categoria categoria;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public List<Filme> index(){
        return repository.findAll();
    }

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String descricao, @PageableDefault(size = 5) Pageable pageable) {
        Page<Filme> despesas = (descricao == null)?
            repository.findAll(pageable):
            repository.findByDescricaoContaining(descricao, pageable);

        return assembler.toModel(despesas.map(Filme::toEntityModel));
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Filme filme){
        log.info("Cadastrando filme " + filme);
        repository.save(filme);
        //return ResponseEntity.status(HttpStatus.CREATED).body(filme);
        return ResponseEntity
        .created(filme.toEntityModel().getRequiredLink("self").toUri())
        .body(filme.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Filme> show(@PathVariable Long id){
        log.info("Buscando filme com o id " + id);
        //return ResponseEntity.ok(getFilme(id));
        return getFilme(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Filme> destroy(@PathVariable Long id){
        log.info("Excluindo filme com o id " + id);
        repository.delete(getFilme(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Filme> update(@PathVariable Long id, @RequestBody @Valid Filme filme){
        log.info("Atualizando filme " + filme);
        getFilme(id);
        filme.setId(id);
        repository.save(filme);
        //return ResponseEntity.ok(filme);
        return filme.toEntityModel();
    }

    public Filme getFilme(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Filme não encontrado"));
    }
    
    
}
