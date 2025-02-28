package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface Update<T> {
    T update(String id, T entity);
}