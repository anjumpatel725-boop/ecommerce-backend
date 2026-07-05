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

    public Order placeSingleOrder(Long userId, Long cartId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Address> addresses = addressRepository.findByUserId(userId);

    if (addresses.isEmpty()) {
        throw new RuntimeException("Please add your delivery address.");
    }

    Cart cart = cartRepository.findByIdAndUserId(cartId, userId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

    Product product = cart.getProduct();

    if (product.getStockQuantity() < cart.getQuantity()) {
        throw new RuntimeException("Out of Stock");
    }

    product.setStockQuantity(
            product.getStockQuantity() - cart.getQuantity());

    productRepository.save(product);

    Order order = new Order();

    order.setUser(user);

    order.setStatus("PLACED");

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

for (OrderItem orderItem : items) {
    orderItem.setOrder(order);
}

    order.setTotalAmount(total);

    Order saved = orderRepository.save(order);

    cartRepository.delete(cart);

    return saved;
}
    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}