package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        String paymentId = order.getId();
        Payment savedPayment = paymentRepository.findById(paymentId);
        if (savedPayment == null || !savedPayment.getStatus().equals(PaymentStatus.SUCCESS.getStatus())){
            Payment payment = new Payment(order.getId(), method, PaymentStatus.PENDING.getStatus(), paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;};

    @Override
    public Payment setStatus(Payment payment, String status){
        Payment findPayment = paymentRepository.findById(payment.getId());
        if (findPayment != null) {
            Order order = orderRepository.findById(payment.getId());

            String orderStatus;
            if ("SUCCESS".equals(status)) {
                orderStatus = "SUCCESS";
            } else if ("REJECTED".equals(status)) {
                orderStatus = "FAILED";
            } else {
                orderStatus = "WAITING_PAYMENT"; // Default case
            }

            Payment newPayment = new Payment(payment.getId(), payment.getMethod(), status, payment.getPaymentData());
            Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(), orderStatus);

            // Save updates
            paymentRepository.save(newPayment);
            orderRepository.save(newOrder);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    };

    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    };

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    };
}
