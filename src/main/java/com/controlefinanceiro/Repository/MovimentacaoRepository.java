package com.controlefinanceiro.Repository;

import com.controlefinanceiro.Model.Movimentacao;

import java.util.Collection;

public interface MovimentacaoRepository {
    void salvar(Movimentacao movimentacao);

    Movimentacao buscarPelo(int id);

    Collection<Movimentacao> buscarTodas();

    void remover(int id);
}
