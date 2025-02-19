package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Edited");
        updatedProduct.setProductQuantity(200);

        Product editedProduct = productRepository.edit(updatedProduct, product.getProductID());
        assertEquals("Sampo Cap Edited", editedProduct.getProductName());
        assertEquals(200, editedProduct.getProductQuantity());
        assertEquals(product.getProductID(), editedProduct.getProductID());
    }

    @Test
    void testEditNonExistentProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Rudi");
        updatedProduct.setProductQuantity(200);

        Product editedProduct = productRepository.edit(updatedProduct, "eb557e9f-1c39-460e-8860-71af6af63bd9");
        assertNull(editedProduct);
    }

    @Test
    void testDeleteNonExistentProduct() {
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd9");
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductID());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductID());
        assertNotNull(foundProduct);
        assertEquals(product.getProductID(), foundProduct.getProductID());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    public void testFindById_ProductExists() {
        ProductRepository repository = new ProductRepository();
        Product product = new Product();

        repository.create(product);

        String generatedId = product.getProductID(); // Ambil ID setelah create
        System.out.println("Generated ID: " + generatedId); // Debugging

        Product found = repository.findById(generatedId);

        assertNotNull(found); // Sekarang harus tidak null
        assertEquals(generatedId, found.getProductID()); // Pastikan ID cocok
    }

    @Test
    public void testFindById_ProductNotExists() {
        ProductRepository repository = new ProductRepository(); // Repository kosong

        Product found = repository.findById("99999"); // ID yang tidak ada

        assertNull(found); // Pastikan mengembalikan null
    }

    @Test
    void testFindByIdOfTwoProducts() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);
        Product product2 = new Product();
        product2.setProductName("Sampo Cap Rudi");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Product result = productRepository.findById(product2.getProductID());
        assertEquals(product2.getProductID(), result.getProductID());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bff-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductID(), savedProduct.getProductID());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductID(), savedProduct.getProductID());

        assertFalse(productIterator.hasNext());
    }
}