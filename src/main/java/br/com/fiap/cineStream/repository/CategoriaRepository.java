package br.com.fiap.cineStream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cineStream.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
