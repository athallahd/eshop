package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private Map<String,String> paymentData;

    @Setter
    private String status;

    public Payment(String id, Order order, String method, Map<String, String> PaymentData){

    }
    public Payment(String id, Order order, String method, Map<String, String> PaymentData, String status){

    }
}
