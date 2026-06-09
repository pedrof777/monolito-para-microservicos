package br.com.pferreira.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "meme-service", url = "http://localhost:8082")
public interface MemeServiceClient {

    @PostMapping("/api/sincronizacao/usuarios")
    void sincronizarUsuarios(@RequestBody Map<String, Long> payload);
}
