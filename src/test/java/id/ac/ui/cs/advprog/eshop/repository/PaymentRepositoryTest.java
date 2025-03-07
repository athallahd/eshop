package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> paymentDataBank;
    Map<String, String> paymentDataVoucher;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudrajat");

        paymentDataBank = new HashMap<>();
        paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        paymentDataBank.put("bankName", "BCA");
        paymentDataBank.put("referenceCode", "666");

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d34b", order1,
                "VOUCHER_CODE", paymentDataVoucher);
        Payment payment2 = new Payment("13652556-012a-4c07-b546-54eb1396d89a", order2,
                "BANK", paymentDataBank);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Map<String, String> paymentDataNew = new HashMap<>();
        paymentDataNew.put("bankName", "BCA");
        paymentDataNew.put("referenceCode", "BCAGACOR");

        Payment newPayment = new Payment(payment.getId(), payment.getOrder(), "BANK", paymentDataNew);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(newPayment.getId(), result.getId());
        assertEquals(newPayment.getId(), findResult.getId());
        assertEquals(newPayment.getMethod(), findResult.getMethod());
        assertEquals(newPayment.getOrder(), findResult.getOrder());
        assertEquals(newPayment.getStatus(), findResult.getStatus());
        assertEquals(newPayment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("abcdefgh");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> checkSize = paymentRepository.findAll();

        assertEquals(2, checkSize.size());
    }
}
