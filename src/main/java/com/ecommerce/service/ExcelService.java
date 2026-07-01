package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class ExcelService {

    @Autowired
    private OrderRepository orderRepository;

    public ByteArrayInputStream exportOrders() throws Exception {

        String[] columns = {"Order ID", "User Email", "Amount", "Status"};

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        Row header = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        List<Order> orders = orderRepository.findAll();

        int rowIdx = 1;

        for (Order o : orders) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(o.getId());

            row.createCell(1).setCellValue(
                    o.getUser() != null ? o.getUser().getEmail() : "N/A"
            );

            row.createCell(2).setCellValue(
                    o.getTotalAmount() != null ? o.getTotalAmount().doubleValue() : 0
            );

            row.createCell(3).setCellValue(
                    o.getStatus() != null ? o.getStatus() : "N/A"
            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}

