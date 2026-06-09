package br.com.ebac.memelandia.controllers;

import br.com.ebac.memelandia.feign.MemeServiceClient;
import br.com.ebac.memelandia.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/memelandia")
public class ControllerMemelandia {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private MemeServiceClient memeServiceClient;

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Map<String, Object> dadosUsuario){
        try {
            Map<String, Object> usuarioCriado = userServiceClient.encaminharCadastroUsuario(dadosUsuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioCriado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao delegar cadastro para user-service!");
        }
    }

    @PostMapping("/memes")
    public ResponseEntity<?> cadastrarMeme(@RequestBody Map<String, Object> dadosMeme){
        try {
            Map<String, Object> usuarioCriado = memeServiceClient.encaminharCadastroMeme(dadosMeme);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioCriado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao delegar cadastro para meme-service!");
        }
    }
}
