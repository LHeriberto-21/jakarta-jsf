package com.heriberto.webapp.jsf.services.impl;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.models.Product;
import com.heriberto.webapp.jsf.repo.inter.CrudRepository;
import com.heriberto.webapp.jsf.repo.inter.IProductRepository;
import com.heriberto.webapp.jsf.services.inter.IProductService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductService implements IProductService {

    @Inject
    private IProductRepository productRepository;

    @Inject
    private CrudRepository<Category> categoryRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> byId(Long id) {
        return Optional.ofNullable(productRepository.byId(id));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> byIdCategory(Long id) {
        return Optional.ofNullable(categoryRepository.byId(id));
    }
}
