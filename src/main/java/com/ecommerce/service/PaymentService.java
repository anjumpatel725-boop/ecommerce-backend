package com.ecommerce.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

   public String createOrder(Integer amount) throws Exception {

    System.out.println("KEY = " + key);
    System.out.println("SECRET = " + secret);
    System.out.println("AMOUNT = " + amount);

    RazorpayClient client = new RazorpayClient(key, secret);

    JSONObject options = new JSONObject();
    options.put("amount", amount * 100);
    options.put("currency", "INR");
    options.put("receipt", "receipt_" + System.currentTimeMillis());

    Order order = client.orders.create(options);

    System.out.println(order);

    return order.toString();
}
}