package com.antspace.GraphNest.resolver;

import com.antspace.GraphNest.models.Order;
import com.antspace.GraphNest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderResolver {
    private final OrderService orderService;

    @QueryMapping
    public List<Order> orders() {
        return orderService.getOrders();
    }

    @MutationMapping
    public Order addOrder(@Argument Long userId, @Argument List<Long> productIds) {
        return orderService.addOrder(userId, productIds);
    }

    @MutationMapping
    public boolean deleteOrder(@Argument Long id) {
        return orderService.deleteOrder(id);
    }
}
