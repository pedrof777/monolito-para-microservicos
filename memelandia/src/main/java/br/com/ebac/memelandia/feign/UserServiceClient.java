package br.com.ebac.memelandia.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "user-server-client", url = "http://localhost:8081")
public interface UserServiceClient {

    @PostMapping("/api/usuarios")
    Map<String, Object> encaminharCadastroUsuario(@RequestBody Map<String, Object> dadosUsuario);
}
