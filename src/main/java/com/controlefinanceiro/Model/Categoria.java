package com.controlefinanceiro.Model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Categoria extends EntidadeBase implements Serializable {

    private String descricao;

    public Categoria() {
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    public void alterar(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {return descricao;}
}
