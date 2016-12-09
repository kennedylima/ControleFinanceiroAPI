package com.controlefinanceiro.Movimentacao;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Model.Movimentacao;
import com.controlefinanceiro.Model.Tipo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class MovimentacaoRestTeste {

    private Movimentacao movimentacao;
    private String descricao;
    private Tipo tipo;
    private Date data;
    private Categoria categoria;
    private Double valor;
    private int idCategoria;
    private int idMovimentacao;

    @Before
    public void inicializar(){
        tipo = Tipo.DESPESA;
        descricao = "Sushi";
        data = new Date();
        valor = 100d;
        categoria = new Categoria("Comida");
    }

    @Test
    public void deve_salvar_uma_movimentacao(){
        salvarMovimentacao();
    }

    @Test
    public void deve_buscar_todas_as_movimentacoes(){
        given().
                accept(MediaType.APPLICATION_JSON_VALUE).
            expect().
                statusCode(200).
            when().
                get("http://localhost:8080/movimentacao");
    }

    @Test
    public void deve_buscar_uma_movimentacao_pelo_id(){
        salvarMovimentacao();
        movimentacao =
                given().
                    accept(MediaType.APPLICATION_JSON_VALUE).
                expect().
                        statusCode(200).
                when().
                    get("http://localhost:8080/movimentacao/"+idMovimentacao).as(Movimentacao.class);

        assertNotNull(movimentacao);
    }

    @Test
    public void deve_remover_uma_movimentacao(){
        salvarMovimentacao();
        given().
                accept(MediaType.APPLICATION_JSON_VALUE).
            expect().
                statusCode(200).
            when().
                delete("http://localhost:8080/movimentacao/"+idMovimentacao);
    }


    public void salvarCategoria(){
        idCategoria =
                Integer.parseInt(
                        given().
                            contentType(MediaType.APPLICATION_JSON_VALUE).
                            body(categoria).
                        expect().
                            statusCode(200).
                        when().
                            post("http://localhost:8080/categoria").asString()
                );

    }

    public void buscarCategoriaPeloId() {
        categoria = new Categoria();
        categoria =
                given().
                    accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                    get("http://localhost:8080/categoria/" + idCategoria).
                    as(Categoria.class);
    }

    public void salvarMovimentacao(){
        salvarCategoria();
        buscarCategoriaPeloId();
        movimentacao = new Movimentacao(tipo,data,descricao,categoria,valor);
        idMovimentacao =
                Integer.parseInt(
                    given().
                        contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(movimentacao).
                    expect().
                        statusCode(200).
                    when().
                        post("http://localhost:8080/movimentacao").asString()
                );
    }
}
