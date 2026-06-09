package br.com.pferreira.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO_CACHE")
public class UsuarioCache {

    @Id
    private Long id;

    public UsuarioCache(){}
    public UsuarioCache(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
