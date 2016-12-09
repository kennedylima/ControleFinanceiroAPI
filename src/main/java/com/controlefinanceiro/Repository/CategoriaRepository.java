package com.controlefinanceiro.Repository;

import com.controlefinanceiro.Model.Categoria;

import java.util.ArrayList;
import java.util.Collection;

public interface CategoriaRepository {
    void salvar(Categoria categoria);

    Collection<Categoria> buscarTodas();

    void remover(int id);

    Categoria buscarPelo(int id);
}
