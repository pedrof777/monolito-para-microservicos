package br.com.pferreira.controllers;

import br.com.pferreira.entities.Meme;
import br.com.pferreira.repositories.RepositorioMeme;
import br.com.pferreira.services.ServicoMeme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memes")
public class MemeController {

    private static final Logger log = LoggerFactory.getLogger(MemeController.class);

    @Autowired
    private ServicoMeme servicoMeme;

    @PostMapping
    public ResponseEntity<Meme> criarMeme(@RequestBody Meme meme){

        log.info("[Meme-service] Tentando postar meme '{}' associado ao usuário ID: {}"
                ,meme.getNome(), meme.getUsuarioId());

        Meme memeCadastrado = servicoMeme.cadastrar(meme);

        log.info("[Meme-service] Sucesso! Meme '{}' salvo com ID {}"
                ,memeCadastrado.getNome(), memeCadastrado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(memeCadastrado);
    }

    @GetMapping("/do-dia")
    public ResponseEntity<Meme> getMemeDoDia(){
        log.info("[Meme-service] Endpoint '/do-dia' acionado para sorteio");
        return servicoMeme.obterMemeAleatorio()
                .map(meme -> {
                    log.info("[Meme-service] Meme do dia selecionado: ID {}", meme.getId());
                    return ResponseEntity.ok(meme);
                })
                .orElseGet(() -> {
                    log.warn("[Meme-service] Sorteio falhou: nenhum meme cadastrado na base.");
                    return ResponseEntity.notFound().build();
                });
    }
}
