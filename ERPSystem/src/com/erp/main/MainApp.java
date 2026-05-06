package com.erp.main;

import java.util.Scanner;
import com.erp.dao.UserDAO;
import com.erp.model.User;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO dao = new UserDAO();

        System.out.println("==== ERP LOGIN ====");
        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        User loggedUser = dao.login(user, pass);

        if (loggedUser != null) {
            System.out.println("Login Successful!");
            System.out.println("Welcome: " + loggedUser.getUsername());
            System.out.println("Role: " + loggedUser.getRole());
        } else {
            System.out.println("Invalid Login!");
        }

        sc.close();
    }
}