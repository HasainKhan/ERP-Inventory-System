package com.erp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.chart.*;

import com.erp.dao.UserDAO;
import com.erp.model.Inventory;
import com.erp.util.CSVGenerator;
import com.erp.util.AppSession;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardController {

    @FXML private TableView<Inventory> table;

    @FXML private TableColumn<Inventory, Integer> colId;
    @FXML private TableColumn<Inventory, String> colName;
    @FXML private TableColumn<Inventory, Integer> colStock;
    @FXML private TableColumn<Inventory, Integer> colROL;
    @FXML private TableColumn<Inventory, Double> colPrice;

    @FXML private TextField txtName;
    @FXML private TextField txtStock;
    @FXML private TextField txtROL;
    @FXML private TextField txtPrice;
    @FXML private TextField txtSearch;

    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnExport;

    @FXML private LineChart<String, Number> chart;

    @FXML private Label alertLabel;

    // DASHBOARD CARDS
    @FXML private Label lblTotalProducts;
    @FXML private Label lblLowStock;
    @FXML private Label lblInventoryValue;
    @FXML private Label lblUsers;

    @FXML
    public void initialize() {

        // TABLE COLUMNS
        colId.setCellValueFactory(data ->
            new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getId()).asObject());

        colName.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(
                data.getValue().getProductName()));

        colStock.setCellValueFactory(data ->
            new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getCurrentStock()).asObject());

        colROL.setCellValueFactory(data ->
            new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getReorderLevel()).asObject());
        colPrice.setCellValueFactory(data ->
        new javafx.beans.property.SimpleDoubleProperty(
            data.getValue().getPrice()).asObject());

        // LOAD DATA
        loadData();

        // ROLE CHECK
        String role =
            AppSession.getUser().getRole();

        if (role.equalsIgnoreCase("Employee")) {

            btnAdd.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            btnExport.setDisable(true);
        }

        // AUTO FILL FORM
        table.setOnMouseClicked(event -> {

            Inventory selected =
                table.getSelectionModel().getSelectedItem();

            if (selected != null) {

                txtName.setText(
                    selected.getProductName());

                txtStock.setText(
                    String.valueOf(
                        selected.getCurrentStock()));

                txtROL.setText(
                    String.valueOf(
                        selected.getReorderLevel()));

                txtPrice.setText(
                    String.valueOf(
                        selected.getPrice()));
            }
        });
    }

    // LOAD INVENTORY
    private void loadData() {

        UserDAO dao =
            new UserDAO();

        List<Inventory> list =
            dao.getInventory();

        table.setItems(
            FXCollections.observableArrayList(list));

        chart.getData().clear();

        loadChart(list);

        checkAlerts(list);

        loadStatistics(list);
    }

    // ADD PRODUCT
    @FXML
    private void handleAdd() {

        try {

            String name =
                txtName.getText();

            int stock =
                Integer.parseInt(
                    txtStock.getText());

            int rol =
                Integer.parseInt(
                    txtROL.getText());

            double price =
                Double.parseDouble(
                    txtPrice.getText());

            Inventory item =
                new Inventory(
                    0,
                    name,
                    stock,
                    rol,
                    price
                );

            UserDAO dao =
                new UserDAO();

            dao.addInventory(item);

            loadData();

            clearFields();

            System.out.println(
                "Product Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE PRODUCT
    @FXML
    private void handleUpdate() {

        Inventory selected =
            table.getSelectionModel().getSelectedItem();

        if (selected != null) {

            try {

                Inventory updated =
                    new Inventory(
                        selected.getId(),
                        txtName.getText(),
                        Integer.parseInt(
                            txtStock.getText()),
                        Integer.parseInt(
                            txtROL.getText()),
                        Double.parseDouble(
                            txtPrice.getText())
                    );

                UserDAO dao =
                    new UserDAO();

                dao.updateInventory(updated);

                loadData();

                clearFields();

                System.out.println(
                    "Updated!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE PRODUCT
    @FXML
    private void handleDelete() {

        Inventory selected =
            table.getSelectionModel().getSelectedItem();

        if (selected != null) {

            UserDAO dao =
                new UserDAO();

            dao.deleteInventory(
                selected.getId());

            loadData();

            clearFields();

            System.out.println(
                "Deleted!");
        }
    }

    // SEARCH PRODUCT
    @FXML
    private void handleSearch() {

        String keyword =
            txtSearch.getText()
                     .toLowerCase();

        UserDAO dao =
            new UserDAO();

        List<Inventory> filtered =
            dao.getInventory()
               .stream()
               .filter(item ->
                   item.getProductName()
                       .toLowerCase()
                       .contains(keyword))
               .collect(Collectors.toList());

        table.setItems(
            FXCollections.observableArrayList(filtered));
    }

    // CHART
    private void loadChart(List<Inventory> list) {

        XYChart.Series<String, Number> series =
            new XYChart.Series<>();

        series.setName(
            "Stock Levels");

        for (Inventory item : list) {

            series.getData().add(
                new XYChart.Data<>(
                    item.getProductName(),
                    item.getCurrentStock()
                )
            );
        }

        chart.getData().add(series);
    }

    // ALERTS
    private void checkAlerts(List<Inventory> list) {

        StringBuilder alerts =
            new StringBuilder();

        for (Inventory item : list) {

            if (item.getCurrentStock()
                    <= item.getReorderLevel()) {

                alerts.append("⚠ Low Stock: ")
                      .append(item.getProductName())
                      .append("\n");
            }
        }

        if (alerts.length() == 0) {

            alertLabel.setText(
                "All stock levels are safe ✅");

        } else {

            alertLabel.setText(
                alerts.toString());
        }
    }

    // DASHBOARD STATISTICS
    private void loadStatistics(List<Inventory> list) {

        // TOTAL PRODUCTS
        lblTotalProducts.setText(
            String.valueOf(list.size())
        );

        // LOW STOCK COUNT
        long lowStock =
            list.stream()
                .filter(item ->
                    item.getCurrentStock()
                        <= item.getReorderLevel())
                .count();

        lblLowStock.setText(
            String.valueOf(lowStock)
        );

        // TOTAL INVENTORY VALUE
        double totalValue = 0;

        for (Inventory item : list) {

            totalValue +=
                item.getCurrentStock()
                * item.getPrice();
        }

        lblInventoryValue.setText(
            "₹ " + totalValue
        );

        // ACTIVE USERS
        lblUsers.setText("2");
    }

    // EXPORT CSV
    @FXML
    private void handleExport() {

        System.out.println(
            "Export clicked");

        UserDAO dao =
            new UserDAO();

        CSVGenerator.generateCSV(
            dao.getInventory());
    }

    // CLEAR INPUT FIELDS
    private void clearFields() {

        txtName.clear();
        txtStock.clear();
        txtROL.clear();
        txtPrice.clear();
    }

    // SIDEBAR - DASHBOARD
    @FXML
    private void handleDashboard() {

        loadData();

        System.out.println(
            "Dashboard Loaded");
    }

    // SIDEBAR - INVENTORY
    @FXML
    private void handleInventory() {

        table.requestFocus();

        System.out.println(
            "Inventory Focused");
    }

    // SIDEBAR - REPORTS
    @FXML
    private void handleReports() {

        handleExport();

        System.out.println(
            "Report Exported");
    }

    // SIDEBAR - LOGOUT
    @FXML
    private void handleLogout() {

        try {

            javafx.fxml.FXMLLoader loader =
                new javafx.fxml.FXMLLoader(
                    getClass().getResource("/login.fxml")
                );

            javafx.scene.Scene scene =
                new javafx.scene.Scene(loader.load());

            scene.getStylesheets().add(
                getClass()
                .getResource("/style.css")
                .toExternalForm()
            );

            javafx.stage.Stage stage =
                (javafx.stage.Stage)
                    table.getScene().getWindow();

            stage.setScene(scene);

            stage.setTitle("ERP Login");

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}