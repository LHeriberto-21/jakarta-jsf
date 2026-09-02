package com.heriberto.webapp.jsf.repo.inter;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    T byId(Long id);
    void save(T t);
    void deleteById(Long id);

}
