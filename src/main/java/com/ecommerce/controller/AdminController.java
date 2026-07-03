package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.ExcelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "https://ecommerce-backend-production-075f.up.railway.app")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ExcelService excelService;

    // GET ALL USERS
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    // GET ALL ORDERS
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(adminService.getOrders());
    }

    // UPDATE ORDER STATUS
    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        Order updatedOrder = adminService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // EXPORT EXCEL
    @GetMapping("/orders/export/excel")
public ResponseEntity<byte[]> exportOrdersExcel() {
    try {
        ByteArrayInputStream in = excelService.exportOrders();
        byte[] excelData = in.readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=orders.xlsx"
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(excelData);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}
}