package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface ProductRepository extends Create<Product>, Find<Product> {
    Product create(Product product);
    Product findById(String productId);
    Product delete(String productId);
    Product edit(Product product, String productId);
    Iterator<Product> findAll();
}