package br.com.pferreira.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEME")
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(name = "URL_MIDIA", nullable = false)
    private String urlMidia;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaMeme categoriaMeme;

    @Column(name = "USUARIO_ID", nullable = false)
    private Long usuarioId;

    public Meme(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlMidia() {
        return urlMidia;
    }

    public void setUrlMidia(String urlMidia) {
        this.urlMidia = urlMidia;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public CategoriaMeme getCategoriaMeme() {
        return categoriaMeme;
    }

    public void setCategoriaMeme(CategoriaMeme categoriaMeme) {
        this.categoriaMeme = categoriaMeme;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
