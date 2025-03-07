package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Payment testPayment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setup() {
        paymentRepository = new PaymentRepository();
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        testPayment = new Payment("2988ae9d-e3bb-4390-9494-f2c15765c354", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), paymentData);
    }

    @Test
    void testSaveCreate() {
        Payment result = paymentRepository.save(testPayment);
        assertEquals(testPayment.getId(), result.getId());

        Payment findResult = paymentRepository.findById(testPayment.getId());
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getId(), findResult.getId());
        assertEquals(testPayment.getMethod(), findResult.getMethod());
        assertEquals(testPayment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(testPayment);
        Payment newPayment = new Payment(testPayment.getId(), PaymentMethod.VOUCHER.getMethod(),
                PaymentStatus.REJECTED.getStatus(), paymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(testPayment.getId());
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getId(), findResult.getId());
        assertEquals(testPayment.getMethod(), findResult.getMethod());
        assertEquals(PaymentStatus.REJECTED.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        paymentRepository.save(testPayment);

        Payment findResult = paymentRepository.findById(testPayment.getId());
        assertEquals(testPayment.getId(), findResult.getId());
        assertEquals(testPayment.getStatus(), findResult.getStatus());
        assertEquals(testPayment.getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment findResult = paymentRepository.findById("nonexistent-id");
        assertNull(findResult);
    }
}

