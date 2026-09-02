package com.heriberto.webapp.jsf.services.impl;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.models.Product;
import com.heriberto.webapp.jsf.repo.inter.CrudRepository;
import com.heriberto.webapp.jsf.repo.inter.IProductRepository;
import com.heriberto.webapp.jsf.services.inter.IProductService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class ProductService implements IProductService {

    @Inject
    private IProductRepository productRepository;

    @Inject
    private CrudRepository<Category> categoryRepository;

    @Override
    @PermitAll
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Product> byId(Long id) {
        return Optional.ofNullable(productRepository.byId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Product> getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Category> byIdCategory(Long id) {
        return Optional.ofNullable(categoryRepository.byId(id));
    }
}
