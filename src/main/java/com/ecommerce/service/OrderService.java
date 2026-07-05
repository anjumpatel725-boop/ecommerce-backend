package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public Order placeSingleOrder(Long userId, Long cartId) {

        // Get User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check Address
        List<Address> addresses = addressRepository.findByUserId(userId);

        if (addresses.isEmpty()) {
            throw new RuntimeException("Please add your delivery address.");
        }

        // Get Cart Item
        Cart cart = cartRepository.findByIdAndUserId(cartId, userId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Product product = cart.getProduct();

        // Stock Check
        if (product.getStockQuantity() < cart.getQuantity()) {
            throw new RuntimeException("Out of Stock");
        }

        // Reduce Stock
        product.setStockQuantity(
                product.getStockQuantity() - cart.getQuantity());

        productRepository.save(product);

        // Create Order
        Order order = new Order();

        order.setUser(user);

        // Copy Address to Order
        Address address = addresses.get(0);

        order.setFullName(address.getFullName());
        order.setMobile(address.getMobile());
        order.setHouse(address.getHouse());
        order.setStreet(address.getStreet());
        order.setCity(address.getCity());
        order.setState(address.getState());
        order.setCountry(address.getCountry());
        order.setPincode(address.getPincode());
        order.setOrderDate(LocalDateTime.now());

        order.setStatus("PLACED");

        // Create Order Item
        List<OrderItem> items = new ArrayList<>();

        OrderItem item = new OrderItem();

        item.setOrder(order);

        item.setProduct(product);

        item.setQuantity(cart.getQuantity());

        BigDecimal total = product.getPrice()
                .multiply(BigDecimal.valueOf(cart.getQuantity()));

        item.setPrice(total);

        items.add(item);

        order.setItems(items);

        // Set Order Reference
        for (OrderItem orderItem : items) {
            orderItem.setOrder(order);
        }

        order.setTotalAmount(total);

        // Save Order
        Order saved = orderRepository.save(order);

        // Remove Cart Item
        cartRepository.delete(cart);

        return saved;
    }

    // User Orders
    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}