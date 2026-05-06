package com.erp.util;

import java.io.FileWriter;
import java.util.List;

import com.erp.model.Inventory;

public class CSVGenerator {

    public static void generateCSV(List<Inventory> list) {

        try {

        	String path = "inventory_report.csv";

            FileWriter writer = new FileWriter(path);

            writer.append("Product,Stock,Reorder Level\n");

            for (Inventory item : list) {

                writer.append(item.getProductName())
                      .append(",")
                      .append(String.valueOf(item.getCurrentStock()))
                      .append(",")
                      .append(String.valueOf(item.getReorderLevel()))
                      .append("\n");
            }

            writer.flush();
            writer.close();

            System.out.println("CSV Report Generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}