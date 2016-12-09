package com.controlefinanceiro.Movimentacao;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Model.Movimentacao;
import com.controlefinanceiro.Model.Tipo;
import com.controlefinanceiro.Repository.CategoriaRepository;
import com.controlefinanceiro.Repository.MovimentacaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class MovimentacaoTeste extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    private Movimentacao movimentacao;
    private String descricao;
    private Tipo tipo;
    private Categoria categoria;
    private Double valor;
    private Date data;

    @Before
    public void inicializar() throws ParseException {
        tipo = Tipo.DESPESA;
        descricao = "Sushi";
        categoria = new Categoria("Comida");
        categoriaRepository.salvar(categoria);
        valor = 100d;
        data = new Date();
        movimentacao = new Movimentacao(tipo,data,descricao,categoria,valor);
        movimentacaoRepository.salvar(movimentacao);
    }

    @Test
    public void deve_salvar_uma_movimentacao(){
        assertNotNull(movimentacaoRepository.buscarPelo(movimentacao.getId()));
    }

    @Test
    public void deve_alterar_uma_movimentacao(){
        int idEsperado = movimentacao.getId();
        data = new Date();
        descricao = "Picadinho Lanches";

        movimentacao.alterar(tipo,descricao,data,categoria,valor);
        movimentacaoRepository.salvar(movimentacao);

        assertEquals(idEsperado,movimentacao.getId());
        assertEquals(descricao,movimentacao.getDescricao());

    }

    @Test
    public void deve_buscar_todas_as_movimentacoes(){
        int quantidadeEsperada = 1;
        Collection<Movimentacao> movimentacoes = movimentacaoRepository.buscarTodas();
        assertEquals(quantidadeEsperada,movimentacoes.size());
    }

    @Test
    public void deve_remover_uma_movimentacao(){
        movimentacaoRepository.remover(movimentacao.getId());
        Collection<Movimentacao> movimentacoes = movimentacaoRepository.buscarTodas();

        assertThat(movimentacoes, not(hasItem(movimentacao)));
    }


}
