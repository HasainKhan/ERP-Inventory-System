package com.erp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.erp.dao.UserDAO;
import com.erp.model.User;
import com.erp.util.AppSession;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label message;

    @FXML
    private void handleLogin() {

        UserDAO dao = new UserDAO();

        User loggedUser =
            dao.login(
                username.getText(),
                password.getText()
            );

        if (loggedUser != null) {

            try {

                // ✅ STORE USER SESSION
                AppSession.setUser(loggedUser);

                javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(
                        getClass().getResource("/dashboard.fxml")
                    );

                javafx.scene.Scene scene =
                    new javafx.scene.Scene(loader.load());

                javafx.stage.Stage stage =
                    (javafx.stage.Stage)
                        username.getScene().getWindow();

                stage.setScene(scene);

                stage.setTitle(
                    "ERP Dashboard - "
                    + loggedUser.getRole()
                );

                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            message.setText("Invalid Login!");
        }
    }
}