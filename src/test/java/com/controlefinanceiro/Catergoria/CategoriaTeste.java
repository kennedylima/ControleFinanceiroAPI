package com.controlefinanceiro.Catergoria;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Repository.CategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class CategoriaTeste extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    CategoriaRepository categoriaRepository;
    Categoria categoria;
    String descricao;

    @Before
    public void inicializar(){
        descricao = "Comida";
        categoria = new Categoria(descricao);
        categoriaRepository.salvar(categoria);
    }

    @Test
    public void deve_salvar_uma_categoria(){
        Collection<Categoria> categorias =  categoriaRepository.buscarTodas();
        assertNotNull(categoriaRepository.buscarPelo(categoria.getId()));
    }

    @Test
    public void deve_alterar_uma_categoria(){
        int idEsperado = categoria.getId();
        descricao = "Jantar";
        categoria.alterar(descricao);
        categoriaRepository.salvar(categoria);
        assertEquals(idEsperado,categoria.getId());
        assertEquals(descricao,categoria.getDescricao());
    }

    @Test
    public void deve_buscar_todas_as_categorias(){
        int quantidadeEsperda = 1;
        Collection<Categoria> categorias =  categoriaRepository.buscarTodas();
        assertEquals(quantidadeEsperda, categorias.size());
    }

    @Test
    public void deve_remover_uma_categoria(){
        Collection<Categoria> categorias =  categoriaRepository.buscarTodas();
        categoria =(Categoria) categorias.toArray()[0];
        categoriaRepository.remover(categoria.getId());
        categorias =  categoriaRepository.buscarTodas();
        assertThat(categorias, not(hasItem(categoria)));

    }


}
