package br.com.pferreira.repositories;

import br.com.pferreira.entities.UsuarioCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarioCache extends JpaRepository<UsuarioCache, Long> {
}
