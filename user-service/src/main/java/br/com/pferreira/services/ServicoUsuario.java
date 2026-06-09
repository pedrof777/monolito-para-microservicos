package br.com.pferreira.services;

import br.com.pferreira.entities.Usuario;
import br.com.pferreira.feign.MemeServiceClient;
import br.com.pferreira.repositories.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

@Service
public class ServicoUsuario {

    private static final Logger log = LoggerFactory.getLogger(ServicoUsuario.class);

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private MemeServiceClient memeServiceClient;

    private final RestTemplate restTemplate = new RestTemplate();

    public Usuario cadastrar(Usuario usuario){
        usuario.setDataCadastro(LocalDate.now());
        Usuario usuarioSalvo = repositorioUsuario.save(usuario);

        notificarMemeService(usuarioSalvo.getId());

        return usuarioSalvo;
    }

    @Async
    public void notificarMemeService(Long usuarioId){
        Map<String, Long> payLoad = Collections.singletonMap("usuarioId", usuarioId);

        try {
            log.info("[User-service] Enviando evento de sincronização do usuário ID: {}"
                    , usuarioId);
            memeServiceClient.sincronizarUsuarios(payLoad);
        }catch (Exception e){
            log.error("[User-service] Falha ao sincronizar usuário ID {}. O memeService pode estar offline temporariamente"
                    , usuarioId);
        }
    }
}
