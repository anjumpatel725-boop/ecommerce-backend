package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private OrderRepository orderRepository;

    public byte[] exportOrders() throws Exception {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        // Header Style
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        // Header Row
        Row header = sheet.createRow(0);

        String[] columns = {
                "Order ID",
                "User Email",
                "Customer Name",
                "Mobile",
                "House",
                "Street",
                "City",
                "State",
                "Country",
                "Pincode",
                "Amount",
                "Status"
        };

        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        // Fetch Orders
        List<Order> orders = orderRepository.findAll();

        int rowNum = 1;

        for (Order order : orders) {

            Row row = sheet.createRow(rowNum++);

            // Order ID
            row.createCell(0).setCellValue(
                    order.getId() == null ? 0 : order.getId()
            );

            // User Email
            row.createCell(1).setCellValue(
                    order.getUser() != null
                            ? order.getUser().getEmail()
                            : ""
            );

            // Customer Name
            row.createCell(2).setCellValue(
                    order.getFullName() == null ? "" : order.getFullName()
            );

            // Mobile
            row.createCell(3).setCellValue(
                    order.getMobile() == null ? "" : order.getMobile()
            );

            // House
            row.createCell(4).setCellValue(
                    order.getHouse() == null ? "" : order.getHouse()
            );

            // Street
            row.createCell(5).setCellValue(
                    order.getStreet() == null ? "" : order.getStreet()
            );

            // City
            row.createCell(6).setCellValue(
                    order.getCity() == null ? "" : order.getCity()
            );

            // State
            row.createCell(7).setCellValue(
                    order.getState() == null ? "" : order.getState()
            );

            // Country
            row.createCell(8).setCellValue(
                    order.getCountry() == null ? "" : order.getCountry()
            );

            // Pincode
            row.createCell(9).setCellValue(
                    order.getPincode() == null ? "" : order.getPincode()
            );

            // Amount
            row.createCell(10).setCellValue(
                    order.getTotalAmount() != null
                            ? order.getTotalAmount().doubleValue()
                            : 0
            );

            // Status
            row.createCell(11).setCellValue(
                    order.getStatus() == null ? "" : order.getStatus()
            );
        }

        // IMPORTANT:
        // Railway/Linux par autoSizeColumn() use mat karo.
        // Ye X11FontManager error deta hai.

        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 9000);
        sheet.setColumnWidth(2, 7000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 7000);
        sheet.setColumnWidth(5, 7000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        sheet.setColumnWidth(9, 5000);
        sheet.setColumnWidth(10, 5000);
        sheet.setColumnWidth(11, 5000);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        workbook.write(outputStream);

        workbook.close();

        return outputStream.toByteArray();
    }
}