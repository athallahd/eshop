package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class PaymentTest {
    private Order order;
    private List<Product> products;
    private Map<String, String> paymentDataBank;
    private Map<String, String> paymentDataVoucher;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductID("a2c63228-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, System.currentTimeMillis(), "usertest1", "WAITING_PAYMENT");

        paymentDataBank = new HashMap<>();
        paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        paymentDataBank.put("bankName", "BCA");
        paymentDataBank.put("referenceCode", "666");
    }

    @Test
    void testCreatePaymentDefault(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);

        assertEquals("13652556-012a-4c07-b546-54eb1396d34b", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentData, paymentData.getPaymentData());
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccess(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentReject(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                    "VOUCHER_CODE", paymentDataVoucher, "INVALID");
        });
    }

    @Test
    void testSetStatusToSuccess(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToReject(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalid(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEONGG"));
    }

    @Test
    void testPaymentMethodBank(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "BANK", paymentDataBank);
        assertEquals("BANK", payment.getMethod());
    }

    @Test
    void testPaymentMethodBank(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertEquals("VOUCHER_CODE", payment.getMethod());
    }

    @Test
    void testPaymentVoucherValid(){
        paymentDataVoucher.clear();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentVoucherInvalid(){
        paymentDataVoucher.clear();
        paymentDataVoucher.put("voucherCode", "awiwiwowiw234");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "VOUCHER_CODE", paymentDataVoucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentBankValid(){
        paymentDataBank.clear();
        paymentDataBank.put("bankName", "BCA");
        paymentDataBank.put("referenceCode", "666");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "BANK", paymentDataBank);
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testPaymentBankInvalid(){
        paymentDataBank.clear();
        paymentDataBank.put("bankName", "");
        paymentDataBank.put("referenceCode", "666");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order,
                "BANK", paymentDataBank);
        assertEquals("REJECTED", payment.getStatus());
    }
}