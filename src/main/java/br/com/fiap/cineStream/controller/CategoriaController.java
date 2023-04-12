package br.com.fiap.cineStream.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cineStream.exception.RestNotFoundException;
import br.com.fiap.cineStream.model.Categoria;
import br.com.fiap.cineStream.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){
        log.info("Cadastrando categoria " + categoria);
        repository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("Buscando a categoria com o id " + id);
        return ResponseEntity.ok(getCategoria(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Categoria> destroy(@PathVariable Long id){
        log.info("Excluindo categoria de id " + id);
        repository.delete(getCategoria(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody @Valid Categoria categoria){
        log.info("Atualizando categoria " + categoria);
        getCategoria(id);
        categoria.setId(id);
        repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    private Categoria getCategoria(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Categoria não encontrada"));
    }
    
}
