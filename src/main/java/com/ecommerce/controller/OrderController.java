package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // PLACE ORDER
    
    @PostMapping("/{userId}/{cartId}")
public Order placeSingleOrder(
        @PathVariable Long userId,
        @PathVariable Long cartId) {

    return orderService.placeSingleOrder(userId, cartId);

}

    // GET USER ORDERS
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderService.getOrders(userId);
    }
}