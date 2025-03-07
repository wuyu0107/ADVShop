package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
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

    List<Payment> payments;
    Order order;
    private Map<String, String> validVoucherData;
    Payment testPayment;

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

        payments = new ArrayList<>();
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), validVoucherData);
        payments.add(payment1);

        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af3856b078", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.REJECTED.getStatus(), validVoucherData);
        payments.add(payment2);

        testPayment = payments.get(0);
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getMethod(), validVoucherData);

        assertNotNull(result);
        assertEquals(testPayment.getId(), result.getId());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.get(1);

        // Mock Payment retrieval
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);
        when(orderRepository.findById(payment.getId())).thenReturn(order);

        // Mock saving behavior
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getStatus());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getStatus(), result.getStatus());
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = testPayment;
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "NOOOOAOAO");
        });

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testSetStatusWithNull() {
        assertThrows(NoSuchElementException.class, () -> {
            paymentService.setStatus(testPayment, null);
        });

        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment testPayment = new Payment("29345fe5-26a3-4e48-bbe5-fdfea60540b8", PaymentMethod.VOUCHER.getMethod(), PaymentStatus.SUCCESS.getStatus(), validVoucherData);
        when(paymentRepository.findById(testPayment.getId())).thenReturn(testPayment);

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
