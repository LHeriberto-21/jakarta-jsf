package com.heriberto.webapp.jsf.repo.impl;

import com.heriberto.webapp.jsf.models.Product;
import com.heriberto.webapp.jsf.repo.inter.CrudRepository;
import com.heriberto.webapp.jsf.repo.inter.IProductRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class ProductRepository implements IProductRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p left outer join fetch p.category", Product.class).getResultList();
    }

    @Override
    public Product byId(Long id) {
        // return em.find(Product.class, id);

        return em.createQuery("select p from Product p left outer join fetch p.category where p.id =:id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Product product) {
        if (product.getId() != null && product.getId() > 0) {
            em.merge(product);
        } else {
            em.persist(product);
        }
    }

    @Override
    public void deleteById(Long id) {
        Product p = this.byId(id);
        em.remove(p);
    }


    @Override
    public List<Product> getByName(String name) {

        return em.createQuery("select p from Product p left outer join fetch p.category where p.name like :name",
                Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
