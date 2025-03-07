package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, Order order, String method, String status, Map<String, String> paymentData) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }

        this.id = (id != null && !id.isEmpty()) ? id : UUID.randomUUID().toString();
        this.order = order;
        this.paymentData = paymentData;
        this.method = method;
        this.status = "WAITING_PAYMENT";
    }
}
