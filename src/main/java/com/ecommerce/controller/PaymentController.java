package com.ecommerce.controller;

import com.ecommerce.dto.PaymentDTO;
import com.ecommerce.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins="*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public String createPayment(
            @RequestBody PaymentDTO paymentDTO) throws Exception {

        return paymentService.createOrder(paymentDTO.getAmount());

    }
}