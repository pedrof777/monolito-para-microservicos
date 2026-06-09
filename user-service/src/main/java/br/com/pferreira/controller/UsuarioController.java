package br.com.pferreira.controller;

import br.com.pferreira.entities.Usuario;
import br.com.pferreira.services.ServicoUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private ServicoUsuario servicoUsuario;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        log.info("[User-service] Requisição recebida para cadastrar usuário com email: {}", usuario.getEmail());

        Usuario usuarioCadastrado = servicoUsuario.cadastrar(usuario);

        log.info("[User-service] Usuário cadastrado com sucesso! ID gerado no banco: {}", usuarioCadastrado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
    }
}
