package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Order placeOrder(Long userId) {

        // Get User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check Address
        List<Address> addresses = addressRepository.findByUserId(userId);

        if (addresses.isEmpty()) {
            throw new RuntimeException("Please add your delivery address before placing the order.");
        }

        // Get Cart Items
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Cart cart : cartItems) {

            Product product = cart.getProduct();

            // Stock Check
            if (product.getStockQuantity() < cart.getQuantity()) {
                throw new RuntimeException(product.getName() + " is out of stock.");
            }

            // Reduce Stock
            product.setStockQuantity(
                    product.getStockQuantity() - cart.getQuantity());

            productRepository.save(product);

            // Create Order Item
            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(cart.getQuantity());

            BigDecimal itemPrice = product.getPrice()
                    .multiply(BigDecimal.valueOf(cart.getQuantity()));

            item.setPrice(itemPrice);

            total = total.add(itemPrice);

            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        // Clear Cart
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}