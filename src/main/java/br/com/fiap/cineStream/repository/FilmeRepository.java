package br.com.fiap.cineStream.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cineStream.model.Categoria;
import br.com.fiap.cineStream.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    //Page<Filme> findByNomeContaining(String nome, Pageable pageable);
    //Page<Filme> findByCategoriasContaining(List<Categoria> catagorias, Pageable pageable);
    
}