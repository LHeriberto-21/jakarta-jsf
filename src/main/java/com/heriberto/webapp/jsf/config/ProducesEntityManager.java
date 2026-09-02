package com.heriberto.webapp.jsf.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ProducesEntityManager {

    @PersistenceContext(name = "ejemploJpa")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return entityManager;
    }


}
