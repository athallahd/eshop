package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class PaymentTest {
    private Payment payment;
    private Order order;
    private List<Product> products;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
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
    void testPaymentCreationDefaultStatus() {
        assertEquals("13652556-012a-4c07-b546-54eb1396d34b", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentCreationSuccessStatus() {
        Payment successPayment = new Payment("new-id", order, "BANK_TRANSFER", "SUCCESS", paymentData);
        assertEquals("SUCCESS", successPayment.getStatus());
    }

    @Test
    void testPaymentDataStorage() {
        paymentData.put("bankName", "Bank Mandiri");
        assertEquals("Bank Mandiri", payment.getPaymentData().get("bankName"));
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

    @Test
    void testInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus(""));
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus(null));
    }

    @Test
    void testInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod(""));
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod(null));
    }

    @Test
    void testNullPaymentData() {
        assertThrows(NullPointerException.class, () -> new Payment("id", order, "VOUCHER", "SUCCESS", null));
    }

    @Test
    void testEmptyPaymentData() {
        Map<String, String> emptyPaymentData = new HashMap<>();
        Payment emptyDataPayment = new Payment("id", order, "VOUCHER", "SUCCESS", emptyPaymentData);
        assertTrue(emptyDataPayment.getPaymentData().isEmpty());
    }

    @Test
    void testInvalidPaymentId() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("", order, "VOUCHER", "SUCCESS", paymentData));
        assertThrows(IllegalArgumentException.class, () -> new Payment(null, order, "VOUCHER", "SUCCESS", paymentData));
    }

    // ✅ Happy Path: Valid Voucher Code ✅
    @Test
    void testValidVoucherCode() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("valid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherCodeTooShort() {
        paymentData.put("voucherCode", "ESHOP1234ABC56"); // Only 14 chars
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeTooLong() {
        paymentData.put("voucherCode", "ESHOP1234ABC56789"); // 17 chars
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeDoesNotStartWithESHOP() {
        paymentData.put("voucherCode", "INVALID1234ABC567"); // Does not start with ESHOP
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeDoesNotContainEightNumbers() {
        paymentData.put("voucherCode", "ESHOP12ABCDEF567"); // Only 6 numbers
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeIsEmpty() {
        paymentData.put("voucherCode", "");
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeIsNull() {
        paymentData.put("voucherCode", null);
        payment = new Payment("invalid-id", order, "VOUCHER", "PENDING", paymentData);
        payment.validateVoucherCode();

        assertEquals("REJECTED", payment.getStatus());
    }
}