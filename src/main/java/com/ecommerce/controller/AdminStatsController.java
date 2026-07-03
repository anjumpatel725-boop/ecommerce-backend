package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/admin/stats")
@CrossOrigin(origins = "*")
public class AdminStatsController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/revenue")
    public Map<String, Object> getRevenue() {

        List<Order> orders = orderRepository.findAll();

        BigDecimal revenue = BigDecimal.ZERO;

        for (Order order : orders) {
            if (order.getTotalAmount() != null) {
                revenue = revenue.add(order.getTotalAmount());
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("totalRevenue", revenue);
        map.put("totalOrders", orders.size());

        return map;
    }
}