package br.com.pferreira.repositories;

import br.com.pferreira.entities.CategoriaMeme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioCategoriaMeme extends JpaRepository<CategoriaMeme, Long> {

    Optional<CategoriaMeme> findByNomeIgnoreCase(String nome);
}
