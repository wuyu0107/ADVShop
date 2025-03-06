package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    private static final List<String> validMethod = Arrays.asList("Bank Transfer", "Voucher");


    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        if (!validMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }

        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
