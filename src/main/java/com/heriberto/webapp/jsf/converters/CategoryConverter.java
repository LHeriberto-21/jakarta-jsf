package com.heriberto.webapp.jsf.converters;

import com.heriberto.webapp.jsf.models.Category;
import com.heriberto.webapp.jsf.services.inter.IProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;


@RequestScoped
@Named("categoriaConverter")
public class CategoryConverter implements Converter<Category> {

    @Inject
    private IProductService productService;


    @Override
    public Category getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if (id == null) {
            return null;
        }

        Optional<Category> categoryOptional = productService.byIdCategory(Long.valueOf(id));

        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Category category) {
        if (category == null) {
            return "0";
        }

        return category.getId().toString();
    }
}
