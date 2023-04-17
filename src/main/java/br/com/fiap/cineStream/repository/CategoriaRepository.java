package br.com.fiap.cineStream.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cineStream.model.Categoria;
import br.com.fiap.cineStream.model.Filme;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    //Page<Categoria> findByNomeContaining(String nome, Pageable pageable);
    //Page<Categoria> findByFilmesContaining(List<Filme> filmes, Pageable pageable);
    
}
