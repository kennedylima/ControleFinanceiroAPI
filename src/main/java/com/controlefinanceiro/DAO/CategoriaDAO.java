package com.controlefinanceiro.DAO;

import com.controlefinanceiro.Model.Categoria;
import com.controlefinanceiro.Repository.CategoriaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class CategoriaDAO implements CategoriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CategoriaDAO() {}

    public CategoriaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void salvar(Categoria categoria) {
        if(categoria.getId() > 0){
            entityManager.merge(categoria);
        }else{
            entityManager.persist(categoria);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public Collection<Categoria> buscarTodas() {
        return entityManager.createQuery("from Categoria c").getResultList();

    }



    @Override
    public Categoria buscarPelo(int id) {
        return entityManager.find(Categoria.class,id);
    }

    @Override
    public void remover(int id) {
        entityManager.remove(entityManager.getReference(Categoria.class, id));
    }


}
