package br.com.fiap.cineStream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cineStream.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
