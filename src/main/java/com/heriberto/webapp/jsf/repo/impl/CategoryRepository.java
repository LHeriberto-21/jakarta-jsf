package com.heriberto.webapp.jsf.repo.impl;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.repo.inter.CrudRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class CategoryRepository implements CrudRepository<Category> {

    @Inject
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category byId(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category category) {
        if (category.getId() != null && category.getId() > 0) {
            em.merge(category);
        } else {
            em.persist(category);
        }
    }

    @Override
    public void deleteById(Long id) {
        Category c = byId(id);
        em.remove(c);
    }
}
