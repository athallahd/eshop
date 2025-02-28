package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductID(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Product edit(Product product, String productId){
        Product editedProduct = findById(productId);

        if (editedProduct != null) {
            editedProduct.setProductName(product.getProductName());
            editedProduct.setProductQuantity(product.getProductQuantity());
        }
        return editedProduct;
    }

    public Product delete(String productId){
        Product deletedProduct = findById(productId);
        if (deletedProduct != null) {
            productData.remove(deletedProduct);
        }
        return deletedProduct;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String productID) {
        for (Product product : productData) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }
}