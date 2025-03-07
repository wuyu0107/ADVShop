package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> paymentData;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String, String>();

        // Set up valid voucher data
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        // Set up invalid voucher data
        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDCODE");

    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("Fictional Book", "$9 Harry Potter");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), paymentData);

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
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), paymentData);

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
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), "SUCCESS", paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getStatus());

        assertEquals(PaymentStatus.SUCCESS.getStatus(), payment.getStatus());
    }

    @Test
    void testSetStatusInvalid () {
        paymentData.put("Voucher Code", "BOOKSFORBOOKS284");
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), paymentData);

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("NOOO"));
    }

    @Test
    void testCreatePaymentWithValidVoucher() {
        Payment payment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), validVoucherData);
        assertNotNull(payment);
        assertEquals(PaymentMethod.VOUCHER.getMethod(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getStatus(), payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.PENDING.getStatus(), invalidVoucherData);
        });
    }
}
