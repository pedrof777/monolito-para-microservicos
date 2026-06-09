package br.com.pferreira.services;

import br.com.pferreira.entities.CategoriaMeme;
import br.com.pferreira.entities.Meme;
import br.com.pferreira.repositories.RepositorioCategoriaMeme;
import br.com.pferreira.repositories.RepositorioMeme;
import br.com.pferreira.repositories.RepositorioUsuarioCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ServicoMeme {

    @Autowired
    private RepositorioMeme repositorioMeme;

    @Autowired
    private RepositorioCategoriaMeme repositorioCategoriaMeme;


    @Autowired
    private RepositorioUsuarioCache repositorioUsuarioCache;

    @Transactional
    public Meme cadastrar(Meme meme){

        validarExistenciaUsuario(meme.getUsuarioId());

        CategoriaMeme categoriaDefinida = obterOuCriarCatmeme(meme.getCategoriaMeme());
        meme.setCategoriaMeme(categoriaDefinida);

        meme.setDataCadastro(LocalDate.now());
        return repositorioMeme.save(meme);
    }

    public Optional<Meme> obterMemeAleatorio(){
        return repositorioMeme.buscarMemeAleatorio();
    }

    //METODOS
    private void validarExistenciaUsuario(Long usuarioId){
        boolean usuarioExiste = repositorioUsuarioCache.existsById(usuarioId);
        if (!usuarioExiste){
            throw new IllegalArgumentException("Cadastro rejeitado: Usuário não foi sincronizado!");
        }
    }

    private void verificaCat(CategoriaMeme categoriaMeme){
        if(categoriaMeme == null || categoriaMeme.getNome() == null){
            throw new IllegalArgumentException("Todo meme precisa necessariamente ter uma categoria.");
        }
    }

    private CategoriaMeme obterOuCriarCatmeme(CategoriaMeme categoriaMeme){

        verificaCat(categoriaMeme);

        String nomeCat = categoriaMeme.getNome().trim();

        return repositorioCategoriaMeme
                .findByNomeIgnoreCase(nomeCat)
                .orElseGet(() -> criarNovaCat(categoriaMeme, nomeCat));
    }

    private CategoriaMeme criarNovaCat(CategoriaMeme novaCat, String nomeCat){
        novaCat.setNome(nomeCat);
        novaCat.setDataCadastro(LocalDate.now());
        return repositorioCategoriaMeme.save(novaCat);
    }
}
