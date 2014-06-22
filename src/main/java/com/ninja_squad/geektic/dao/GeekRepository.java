package com.ninja_squad.geektic.dao;

import com.ninja_squad.geektic.model.Geek;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GeekRepository {

    @PersistenceContext
    private EntityManager em;

    List<Geek> findAll() {
        return em.createQuery("select g from Geek g", Geek.class).getResultList();
    }
}
