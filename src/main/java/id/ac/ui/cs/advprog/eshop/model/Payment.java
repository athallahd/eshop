package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private Map<String,String> paymentData;

    @Setter
    private String status;

    public Payment(String id, Order order, String method, Map<String, String> paymentData){
        this.id = id;
        this.order = order;
        this.method = method;
        this.setStatusPaymentData(paymentData, method);
        this.paymentData = paymentData;
    }
    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status){
        this(id, order, method, paymentData);

        String[] statusList = {"PENDING","SUCCESS", "REJECTED"};
        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        }else{
            this.status = status;
        }
    }

    public void setStatus(String status){
        String[] statusList = {"PENDING","SUCCESS", "REJECTED"};
        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        }else{
            this.status = status;
        }
    }

    public void setStatusPaymentData(Map<String, String> paymentData, String paymentMethod) {
        if (paymentMethod.equals("BANK")) {
            if (paymentData.get("bankName") == null || paymentData.get("referenceCode") == null || Objects.equals(paymentData.get("bankName"), "")
                    || Objects.equals(paymentData.get("referenceCode"), "")) {
                this.status = "REJECTED";
            }else{
                this.status = "PENDING";
            }
        }else if (paymentMethod.equals("VOUCHER_CODE")) {
            String voucherCode = paymentData.get("voucherCode");

            if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
                this.status = "REJECTED";
                return;
            }

            long digitCount = voucherCode.chars()
                    .filter(Character::isDigit)
                    .count();

            if (digitCount != 8) {
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }
        }
    }
}
