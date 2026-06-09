package br.com.pferreira.repositories;

import br.com.pferreira.entities.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepositorioMeme extends JpaRepository<Meme, Long> {

    @Query(value = "SELECT * FROM meme ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Meme> buscarMemeAleatorio();
}
