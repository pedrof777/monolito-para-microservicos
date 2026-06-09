package br.com.ebac.memelandia.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "meme-service", url = "http://localhost:8082")
public interface MemeServiceClient {

    @PostMapping("/api/memes")
    Map<String, Object> encaminharCadastroMeme(@RequestBody Map<String, Object> dadosMeme);
}
