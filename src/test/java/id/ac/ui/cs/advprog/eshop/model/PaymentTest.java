package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class PaymentTest {
    private Payment payment;
    private Order order;
    private List<Product> products;
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product = new Product();
        product.setProductID("a2c63228-4a37-4664-83c7-f32db8620155");
        product.setProductName("Sabun Cap Usep");
        product.setProductQuantity(3);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, System.currentTimeMillis(), "usertest1", "WAITING_PAYMENT");

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order, "VOUCHER", "SUCCESS", paymentData);
    }

    @Test
    void testPaymentCreation() {
        assertEquals("13652556-012a-4c07-b546-54eb1396d34b", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetMethod() {
        payment.setMethod("BANK_TRANSFER");
        assertEquals("BANK_TRANSFER", payment.getMethod());
    }

    @Test
    void testSetStatus() {
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
