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

        Font font = workbook.createFont();
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        Row header = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(style);
        }

        List<Order> orders = orderRepository.findAll();

        int row = 1;

        for (Order o : orders) {

            Row r = sheet.createRow(row++);

            r.createCell(0).setCellValue(o.getId());

            r.createCell(1).setCellValue("test@example.com");

            r.createCell(2).setCellValue(o.getFullName());
            r.createCell(3).setCellValue(o.getMobile());
            r.createCell(4).setCellValue(o.getHouse());
            r.createCell(5).setCellValue(o.getStreet());
            r.createCell(6).setCellValue(o.getCity());
            r.createCell(7).setCellValue(o.getState());
            r.createCell(8).setCellValue(o.getCountry());
            r.createCell(9).setCellValue(o.getPincode());

            r.createCell(10).setCellValue(
                    o.getTotalAmount() != null
                            ? o.getTotalAmount().doubleValue()
                            : 0
            );

            r.createCell(2).setCellValue(
        o.getFullName() == null ? "" : o.getFullName()
);
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        out.close();

        return out.toByteArray();
    }

}