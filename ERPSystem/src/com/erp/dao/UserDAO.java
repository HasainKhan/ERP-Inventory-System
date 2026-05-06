package com.erp.dao;

import java.sql.*;
import java.util.*;

import com.erp.db.DBConnection;
import com.erp.model.User;
import com.erp.model.Inventory;

public class UserDAO {

    // 🔐 LOGIN METHOD
    public User login(String username, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new User(
                    rs.getString("username"),
                    rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 📦 GET INVENTORY
    public List<Inventory> getInventory() {

        List<Inventory> list =
            new ArrayList<>();

        try {

            Connection con =
                DBConnection.getConnection();

            String query =
                "SELECT * FROM inventory";

            PreparedStatement ps =
                con.prepareStatement(query);

            ResultSet rs =
                ps.executeQuery();

            while (rs.next()) {

                list.add(
                    new Inventory(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getInt("current_stock"),
                        rs.getInt("reorder_level"),
                        rs.getDouble("price")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ➕ ADD INVENTORY
    public void addInventory(Inventory item) {

        try {

            Connection con =
                DBConnection.getConnection();

            String query =
                "INSERT INTO inventory(product_name,current_stock,reorder_level,price) VALUES(?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setString(1,
                item.getProductName());

            ps.setInt(2,
                item.getCurrentStock());

            ps.setInt(3,
                item.getReorderLevel());

            ps.setDouble(4,
                item.getPrice());

            ps.executeUpdate();

            System.out.println("Product Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✏️ UPDATE INVENTORY
    public void updateInventory(Inventory item) {

        try {

            Connection con =
                DBConnection.getConnection();

            String query =
                "UPDATE inventory SET product_name=?, current_stock=?, reorder_level=?, price=? WHERE id=?";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setString(1,
                item.getProductName());

            ps.setInt(2,
                item.getCurrentStock());

            ps.setInt(3,
                item.getReorderLevel());

            ps.setDouble(4,
                item.getPrice());

            ps.setInt(5,
                item.getId());

            ps.executeUpdate();

            System.out.println("Product Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ❌ DELETE INVENTORY
    public void deleteInventory(int id) {

        try {

            Connection con =
                DBConnection.getConnection();

            String query =
                "DELETE FROM inventory WHERE id=?";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Product Deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}