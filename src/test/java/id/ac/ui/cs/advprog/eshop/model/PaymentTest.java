package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String, String>();
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("Fictional Book", "$9 Harry Potter");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "Voucher", PaymentStatus.SUCCESS.getStatus(), paymentData);

        assertEquals(PaymentStatus.SUCCESS.getStatus(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("Fictional Book", "$9 Harry Potter");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "Book", "NOOOO", paymentData);
        });
    }

    @Test
    void testCreatePaymentValidMethod() {
        paymentData.put("Voucher Code", "BOOKSFORBOOKS284");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "Voucher", PaymentStatus.SUCCESS.getStatus(), paymentData);

        assertEquals("Voucher", payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        paymentData.put("Voucher Code", "BOOKSFORBOOKS284");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "", PaymentStatus.SUCCESS.getStatus(), paymentData);
        });
    }

    @Test
    void testSetStatusValid () {
        paymentData.put("Voucher Code", "BOOKSFORBOOKS284");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "Voucher", "SUCCESS", paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getStatus());

        assertEquals(PaymentStatus.SUCCESS.getStatus(), payment.getStatus());
    }

    @Test
    void testSetStatusInvalid () {
        paymentData.put("Voucher Code", "BOOKSFORBOOKS284");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", "Voucher", PaymentStatus.SUCCESS.getStatus(), paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("NOOO"));
    }
}
