package com.heriberto.webapp.jsf.controllers;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Named
@SessionScoped
public class LanguageController implements Serializable {

    @Serial
    private static final long serialVersionUID = 12831982L;

    private Locale locale;
    private String language;
    private Map<String, String> supportedLanguages;

    @PostConstruct
    public void init() {
        this.locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        this.supportedLanguages = new HashMap<>();
        supportedLanguages.put("Ingles", "en");
        supportedLanguages.put("Español", "es");
    }

    public void select(ValueChangeEvent event) {
        String newLanguage = event.getNewValue().toString();
        supportedLanguages.values().forEach(v -> {
            if (v.equals(newLanguage)){
                this.locale = new Locale(newLanguage);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
            }
        });
    }


    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(Map<String, String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
}
