package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepositoryImpl productRepositoryImpl;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductID("12345");
        sampleProduct.setProductName("Test Product");
        sampleProduct.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepositoryImpl.create(any(Product.class))).thenReturn(sampleProduct);

        Product createdProduct = productService.create(sampleProduct);

        assertNotNull(createdProduct);
        assertEquals(sampleProduct.getProductID(), createdProduct.getProductID());
        verify(productRepositoryImpl, times(1)).create(sampleProduct);
    }

    @Test
    void testEdit() {
        when(productRepositoryImpl.edit(any(Product.class), anyString())).thenReturn(sampleProduct);

        Product editedProduct = productService.edit(sampleProduct, "12345");

        assertNotNull(editedProduct);
        assertEquals(sampleProduct.getProductID(), editedProduct.getProductID());
        verify(productRepositoryImpl, times(1)).edit(sampleProduct, "12345");
    }

    @Test
    void testDelete() {
        when(productRepositoryImpl.delete(anyString())).thenReturn(sampleProduct);

        Product deletedProduct = productService.delete("12345");

        assertNotNull(deletedProduct);
        assertEquals(sampleProduct.getProductID(), deletedProduct.getProductID());
        verify(productRepositoryImpl, times(1)).delete("12345");
    }

    @Test
    void testFindById() {
        when(productRepositoryImpl.findById(anyString())).thenReturn(sampleProduct);

        Product foundProduct = productService.findById("12345");

        assertNotNull(foundProduct);
        assertEquals(sampleProduct.getProductID(), foundProduct.getProductID());
        verify(productRepositoryImpl, times(1)).findById("12345");
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(sampleProduct);

        Iterator<Product> mockIterator = productList.iterator();
        when(productRepositoryImpl.findAll()).thenReturn(mockIterator);

        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        assertEquals(sampleProduct.getProductID(), foundProducts.get(0).getProductID());
        verify(productRepositoryImpl, times(1)).findAll();
    }
}