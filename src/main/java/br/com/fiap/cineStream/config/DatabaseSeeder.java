package br.com.fiap.cineStream.config;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import br.com.fiap.cineStream.model.Usuario;
import br.com.fiap.cineStream.repository.CategoriaRepository;
import br.com.fiap.cineStream.repository.FilmeRepository;
import br.com.fiap.cineStream.repository.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String...args) throws Exception{

        Usuario u1 = new Usuario(1L,"Ruan Santos Dias", new GregorianCalendar(1991, Calendar.JANUARY, 29) , "25498698425", "ruan@email.com");
        Usuario u2 = new Usuario(2L,"Isa Mary", new GregorianCalendar(1989, Calendar.APRIL, 15) , "14494598425", "isa@email.com");
        Usuario u3 = new Usuario(3L,"João Pé de Feijão", new GregorianCalendar(1854, Calendar.DECEMBER, 31) , "12345678910", "feijao@email.com");
        Usuario u4 = new Usuario(4L,"Valentina Pé de Cabrita", new GregorianCalendar(2015, Calendar.AUGUST, 28) , "98765432101", "tininha@email.com");
        usuarioRepository.saveAll(List.of(u1, u2, u3, u4));

        //Categoria c1 = new Categoria(1L, "Ação", null);
       // Categoria c2 = new Categoria(2L, "Aventura", null);
       // Categoria c3 = new Categoria(3L, "Suspense", null);
       // Categoria c4 = new Categoria(4L, "Terror", null);
        //categoriaRepository.saveAll(List.of(c1, c2, c3, c4));

        //filmeRepository.saveAll(List.of(
            //Filme.builder().nome("Snatch").resumo("Resumo do snatch").dataDeLancamento(new GregorianCalendar(2011, Calendar.MAY, 11))
                //.categoria((List<Categoria>) c1).build(),

           // Filme.builder().nome("Harry Potter").resumo("Resumo do hp").dataDeLancamento(new GregorianCalendar(2001, Calendar.NOVEMBER, 23))
               // .categoria((List<Categoria>) c2).build(),

           // Filme.builder().nome("O chamado").resumo("Resumo").dataDeLancamento(new GregorianCalendar(2003, Calendar.JANUARY    , 31))
               // .categoria((List<Categoria>) c3).build(),

           // Filme.builder().nome("Xuxa só para baixinhos").resumo("Resumo").dataDeLancamento(new GregorianCalendar(2006, Calendar.JUNE, 06))
                //.categoria((List<Categoria>) c4).build()
        //));
    }
}
