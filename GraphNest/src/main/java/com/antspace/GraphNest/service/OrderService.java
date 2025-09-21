package com.antspace.GraphNest.service;

import com.antspace.GraphNest.models.Order;
import com.antspace.GraphNest.models.Product;
import com.antspace.GraphNest.models.User;
import com.antspace.GraphNest.repository.OrderRepository;
import com.antspace.GraphNest.repository.ProductRepository;
import com.antspace.GraphNest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Long userId, List<Long> productIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) throw new RuntimeException("No products found for ids: " + productIds);
        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
