package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import enums.PaymentMethod;
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


    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }

        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }

        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;

        if (PaymentMethod.VOUCHER.getMethod().equals(method) && !isValidVoucher(paymentData.get("voucherCode"))) {
            this.status = PaymentStatus.REJECTED.getStatus();
        } else if (PaymentMethod.CASH_ON_DELIVERY.getMethod().equals(method) && !isValidCOD(paymentData)) {
            this.status = PaymentStatus.REJECTED.getStatus();
        } else {
            this.status = status;
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isValidVoucher(String voucherCode) {
        return voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.replaceAll("[^0-9]", "").length() == 8;
    }

    public boolean hasValidField(Map<String, String> data, String key) {
        return data.get(key) != null && !data.get(key).trim().isEmpty();
    }

    public boolean isValidCOD(Map<String, String> paymentData) {
        return hasValidField(paymentData, "address") && hasValidField(paymentData, "deliveryFee");
    }
}
