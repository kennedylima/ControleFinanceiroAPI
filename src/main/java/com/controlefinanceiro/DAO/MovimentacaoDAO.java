package com.controlefinanceiro.DAO;

import com.controlefinanceiro.Model.Movimentacao;
import com.controlefinanceiro.Repository.MovimentacaoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class MovimentacaoDAO implements MovimentacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MovimentacaoDAO() {}

    @Override
    public void salvar(Movimentacao movimentacao) {
        if(movimentacao.getId()> 0){
            entityManager.merge(movimentacao);
        }
        else{
            entityManager.persist(movimentacao);
        }
    }

    @Override
    public Movimentacao buscarPelo(int id) {
        return entityManager.find(Movimentacao.class, id);
    }

    @Override
    public Collection<Movimentacao> buscarTodas() {
        return entityManager.createQuery("from Movimentacao m").getResultList();
    }

    @Override
    public void remover(int id) {
        entityManager.remove(entityManager.getReference(Movimentacao.class,id));
    }
}
