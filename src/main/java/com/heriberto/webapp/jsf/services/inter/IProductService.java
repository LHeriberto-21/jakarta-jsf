package com.heriberto.webapp.jsf.services.inter;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.models.Product;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface IProductService {

    List<Product> findAllProducts();
    Optional<Product> byId(Long id);
    void save(Product product);
    void deleteById(Long id);
    List<Product> getByName(String name);

    List<Category> findAllCategories();
    Optional<Category> byIdCategory(Long id);

}
