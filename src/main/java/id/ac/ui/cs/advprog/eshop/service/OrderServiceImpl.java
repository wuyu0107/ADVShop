package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        // Code Smell: Returning null if an order with the same ID already exists.
        // Impact: This could lead to NullPointerExceptions in client code and makes error handling unclear.
        // Refactoring: Consider throwing an exception or returning an Optional<Order> to better express the outcome.
        // Instead of returning null, throw an exception if the order already exists.

        if (orderRepository.findById(order.getId()) != null) {
            throw new IllegalArgumentException("Order with id " + order.getId() + " already exists.");
        }

        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            // Code Smell: Incorrect parameter passed to the Order constructor.
            // Impact: Passing order.getStatus() instead of order.getAuthor() can lead to data inconsistency.
            // Refactoring: Verify and correct the constructor parameters to accurately reflect the Order's data.
            // Correcting the parameters: use order.getAuthor() instead of order.getStatus()

            Order newOrder = new Order(order.getId(), order.getProducts(),
                    order.getOrderTime(), order.getAuthor(), status);

            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new NoSuchElementException("Order with id " + orderId + " not found.");
        }
    }


    @Override
    public List<Order> findAllByAuthor(String author) {
        return orderRepository.findAllByAuthor(author);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId);
    }

}
