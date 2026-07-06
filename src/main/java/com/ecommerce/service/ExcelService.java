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

        System.out.println("========== EXCEL EXPORT STARTED ==========");

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

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        headerStyle.setFont(font);

        Row header = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        List<Order> orders = orderRepository.findAll();

        System.out.println("Total Orders = " + orders.size());

        int rowIdx = 1;

        for (Order o : orders) {

            try {

                System.out.println("Processing Order ID : " + o.getId());

                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(
                        o.getId() != null ? o.getId() : 0
                );

                row.createCell(1).setCellValue(
                        o.getUser() != null &&
                        o.getUser().getEmail() != null
                                ? o.getUser().getEmail()
                                : ""
                );

                row.createCell(2).setCellValue(
                        o.getFullName() != null
                                ? o.getFullName()
                                : ""
                );

                row.createCell(3).setCellValue(
                        o.getMobile() != null
                                ? o.getMobile()
                                : ""
                );

                row.createCell(4).setCellValue(
                        o.getHouse() != null
                                ? o.getHouse()
                                : ""
                );

                row.createCell(5).setCellValue(
                        o.getStreet() != null
                                ? o.getStreet()
                                : ""
                );

                row.createCell(6).setCellValue(
                        o.getCity() != null
                                ? o.getCity()
                                : ""
                );

                row.createCell(7).setCellValue(
                        o.getState() != null
                                ? o.getState()
                                : ""
                );

                row.createCell(8).setCellValue(
                        o.getCountry() != null
                                ? o.getCountry()
                                : ""
                );

                row.createCell(9).setCellValue(
                        o.getPincode() != null
                                ? o.getPincode()
                                : ""
                );

                row.createCell(10).setCellValue(
                        o.getTotalAmount() != null
                                ? o.getTotalAmount().doubleValue()
                                : 0
                );

                row.createCell(11).setCellValue(
                        o.getStatus() != null
                                ? o.getStatus()
                                : ""
                );

            } catch (Exception ex) {

                System.out.println("ERROR IN ORDER ID : " + o.getId());

                ex.printStackTrace();

                throw ex;
            }

        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        System.out.println("========== EXCEL CREATED SUCCESSFULLY ==========");

        return new ByteArrayInputStream(out.toByteArray());
    }
}