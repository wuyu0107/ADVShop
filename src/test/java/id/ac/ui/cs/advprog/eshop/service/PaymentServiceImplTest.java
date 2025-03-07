package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    Order order;
    Payment testPayment;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> BankData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1709560000L, "Safira Sudrajat");

        // Handle different payment methods
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ESHOP12ABC");

        BankData = new HashMap<>();
        BankData.put("bankCode", "Bank Central Asia");
        BankData.put("referenceCode", "1234567890");
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        Payment testPayment = new Payment("29345fe5-26a3-4e48-bbe5-fdfea60540b8", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.PENDING.getStatus(), validVoucherData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getMethod(), validVoucherData);

        assertNotNull(result);
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getMethod(), result.getMethod());
        assertEquals(testPayment.getStatus(), result.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, PaymentMethod.VOUCHER.getMethod(), invalidVoucherData);
        });

        assertEquals("Invalid voucher code", exception.getMessage());
        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithValidBankTransfer() {
        Payment testPayment = new Payment("29345fe5-26a3-4e48-bbe5-fdfea60540b8", PaymentMethod.BANK_TRANSFER.getMethod(), PaymentStatus.PENDING.getStatus(), BankData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        Payment result = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER.getMethod(), BankData);

        assertNotNull(result);
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getMethod(), result.getMethod());
        assertEquals(testPayment.getStatus(), result.getStatus());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        Payment updatedPayment = paymentService.setStatus(testPayment, PaymentStatus.SUCCESS.getStatus());

        assertEquals(PaymentStatus.SUCCESS.getStatus(), updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(updatedPayment);
    }

    @Test
    void testSetStatusRejected() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        Payment updatedPayment = paymentService.setStatus(testPayment, PaymentStatus.REJECTED.getStatus());

        assertEquals(PaymentStatus.REJECTED.getStatus(), updatedPayment.getStatus());
        verify(paymentRepository, times(1)).save(updatedPayment);
    }

    @Test
    void testSetStatusWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(testPayment, null);
        });

        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment testPayment = new Payment("29345fe5-26a3-4e48-bbe5-fdfea60540b8", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.PENDING.getStatus(), validVoucherData);
        when(paymentRepository.findById("29345fe5-26a3-4e48-bbe5-fdfea60540b8")).thenReturn(testPayment);

        Payment result = paymentService.getPayment(testPayment.getId());

        assertNotNull(result);
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getMethod(), result.getMethod());
        assertEquals(testPayment.getStatus(), result.getStatus());
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("invalid-id")).thenReturn(null);

        Payment result = paymentService.getPayment("invalid-id");
        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = Collections.singletonList(testPayment);
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
    }
}
