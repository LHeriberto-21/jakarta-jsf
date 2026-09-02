package com.heriberto.webapp.jsf.controllers;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.models.Product;
import com.heriberto.webapp.jsf.services.inter.IProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.ResourceBundle;

@Model
public class ProductController {

    @Inject
    private IProductService productService;

    private Product product;

    private Long id;

    private List<Product> listed;

    private String search;

    @Inject
    @Named("msg")
    private ResourceBundle resourceBundle;

    @Inject
    @Named("fc")
    private FacesContext facesContext;


    @PostConstruct
    public void init() {
        this.listed = productService.findAllProducts();
        product = new Product();
    }

    @Produces
    @Model
    public String title() {
        return resourceBundle.getString("product.text.title");
    }

//    @Produces
//    @RequestScoped
//    @Named("listed")
//    public List<Product> findAll() {
//        return productService.findAllProducts();
//    }

//    @Produces
//    @Model
    public Product product() {
        this.product = new Product();
        if (id != null && id > 0) {
            productService.byId(id).ifPresent(p -> this.product = p);
        }
        return product;
    }

    @Produces
    @Model
    public List<Category> categories() {
        return productService.findAllCategories();
    }

    public void edit(Long id) {
        this.id = id;
        product();
    }

    public void save() {
        System.out.println(product);
        if (product.getId() != null && product.getId() > 0 ) {
            facesContext.addMessage(
                    null,
                    new FacesMessage(String.format(resourceBundle.getString("product.message.edited"), product.getName()))
            );
        } else {
            facesContext.addMessage(
                    null,
                    new FacesMessage(String.format(resourceBundle.getString("product.message.created"), product.getName()))
            );
        }
        productService.save(this.product);
        listed = productService.findAllProducts();
        product = new Product();
    }

    public void delete(Product product) {
        productService.deleteById(product.getId());
        facesContext.addMessage(
                null,
                new FacesMessage(String.format(resourceBundle.getString("product.message.deleted"), product.getName()))
        );
        listed = productService.findAllProducts();
    }

    public void search() {
        this.listed = productService.getByName(this.search);
    }

    public void closeDialog() {
        System.out.println("Cerrando la ventana de diálogo");
        product = new Product();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getListed() {
        return listed;
    }

    public void setListed(List<Product> listed) {
        this.listed = listed;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
