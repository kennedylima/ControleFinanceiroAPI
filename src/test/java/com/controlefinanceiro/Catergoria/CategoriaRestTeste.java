package com.controlefinanceiro.Catergoria;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Repository.CategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class CategoriaRestTeste {

    @Autowired
    CategoriaRepository categoriaRepository;
    private Categoria categoria;
    private String descricao;
    private int idCategoria;

    @Before
    public void inicializar(){
        descricao = "Comida";
        categoria = new Categoria(descricao);
    }

    @Test
    public void deve_salvar_uma_categoria(){
        salvarCategoria();
    }

    @Test
    public void deve_buscar_todas_as_categorias(){
        salvarCategoria();
        given().
            accept(MediaType.APPLICATION_JSON_VALUE).
        expect().
            statusCode(200).
        when().
            get("http://localhost:8080/categoria");
    }

    @Test
    public void deve_buscar_uma_categoria_pelo_id(){
        salvarCategoria();
        Categoria categoria =
                given().
                    accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                    get("http://localhost:8080/categoria/"+idCategoria).
                    as(Categoria.class);

        assertNotNull(categoria);
    }

    @Test
    public void deve_remover_uma_categoria(){
        salvarCategoria();
        given().
            accept(MediaType.APPLICATION_JSON_VALUE).
        expect().
            statusCode(200).
        when().
            delete("http://localhost:8080/categoria/"+idCategoria);
    }

    private void salvarCategoria(){
        idCategoria = Integer.parseInt(
                given().
                    contentType(MediaType.APPLICATION_JSON_VALUE).
                    body(categoria).
                expect().
                    statusCode(200).
                when().
                    post("http://localhost:8080/categoria").asString()
        );
    }
}
