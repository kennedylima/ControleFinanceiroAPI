package com.controlefinanceiro.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Movimentacao extends EntidadeBase implements Serializable{

    private  Tipo tipo;

    private  String descricao;

    private Date data;

    @ManyToOne
    private  Categoria categoria;

    private  Double valor;


    public Movimentacao() {}

    public Movimentacao(Tipo tipo,Date data, String descricao, Categoria categoria, Double valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;

    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void alterar(Tipo tipo, String descricao, Date data, Categoria categoria, Double valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }


}
