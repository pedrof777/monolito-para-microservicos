package br.com.pferreira.controllers;

import br.com.pferreira.entities.UsuarioCache;
import br.com.pferreira.repositories.RepositorioUsuarioCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sincronizacao/usuarios")
public class SincronizacaoController {

    private static final Logger log = LoggerFactory.getLogger(SincronizacaoController.class);

    @Autowired
    private RepositorioUsuarioCache repositorioUsuarioCache;

    @PostMapping
    public ResponseEntity<Void> sincronizar(@RequestBody Map<String, Long> payLoad){
        Long usuarioId = payLoad.get("usuarioId");

        if (usuarioId == null ){
            log.error("[Meme-Service] Falha na sincronização: 'usuarioId' veio nulo no payload!");
            return ResponseEntity.badRequest().build();
        }

        log.info("[Meme-service] Evento capturado. Sincronizando ID de usuário: {}", usuarioId);
        repositorioUsuarioCache.save(new UsuarioCache(usuarioId));

        return ResponseEntity.ok().build();
    }
}
