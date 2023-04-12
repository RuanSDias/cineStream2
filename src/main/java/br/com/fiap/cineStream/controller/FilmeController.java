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
import br.com.fiap.cineStream.model.Filme;
import br.com.fiap.cineStream.repository.FilmeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FilmeRepository repository;

    @GetMapping
    public List<Filme> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Filme> create(@RequestBody Filme filme){
        log.info("Cadastrando filme " + filme);
        repository.save(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    @GetMapping("{id}")
    public ResponseEntity<Filme> show(@PathVariable Long id){
        log.info("Buscando filme com o id " + id);
        return ResponseEntity.ok(getFilme(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Filme> destroy(@PathVariable Long id){
        log.info("Excluindo filme com o id " + id);
        repository.delete(getFilme(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Filme> update(@PathVariable Long id, @RequestBody @Valid Filme filme){
        log.info("Atualizando filme " + filme);
        getFilme(id);
        filme.setId(id);
        repository.save(filme);
        return ResponseEntity.ok(filme);
    }

    public Filme getFilme(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Filme não encontrado"));
    }
    
    
}
