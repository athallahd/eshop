package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
public interface Find<T> {
    Iterator<T> findAll();
    T findById(String id);
}
