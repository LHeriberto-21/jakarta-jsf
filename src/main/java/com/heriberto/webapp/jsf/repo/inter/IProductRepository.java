package com.heriberto.webapp.jsf.repo.inter;

import com.heriberto.webapp.jsf.models.Product;

import java.util.List;

public interface IProductRepository extends CrudRepository<Product> {

    List<Product> getByName(String name);
}
