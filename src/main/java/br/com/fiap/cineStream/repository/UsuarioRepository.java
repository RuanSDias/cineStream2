package br.com.fiap.cineStream.repository;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cineStream.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
   // Page<Usuario> findByCpfContaining(String cpf, Pageable pageable);
   // Page<Usuario> findByEmailContaining(String email, Pageable pageable);
    //Page<Usuario> findByDataDeNascimentoContaining(Calendar dataDeNascimento, Pageable pageable);
    Optional<Usuario> findByEmail(String email);
}
