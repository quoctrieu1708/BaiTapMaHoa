package org.example.baitapmahoa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label messageLabel;

  @FXML
  void login(ActionEvent event) {
    String username = usernameField.getText();
    String password = passwordField.getText();
    if (loginUser(username, password)) {
      messageLabel.setText("Welcome, " + username + "!");
    } else {
      messageLabel.setText("Login failed. Please check your credentials.");
    }
  }

  @FXML
  void register(ActionEvent event) {
    String username = usernameField.getText();
    String password = passwordField.getText();
    if (registerUser(username, password)) {
      messageLabel.setText("Registration successful.");
    } else {
      messageLabel.setText("Registration failed.");
    }
  }

  private boolean registerUser(String username, String password) {
    try {
      Connection connection = Database.getConnection();
      String hashedPassword = hashPassword(password);
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, hashedPassword);
      int rowsInserted = preparedStatement.executeUpdate();
      return rowsInserted > 0;
    } catch (SQLException | NoSuchAlgorithmException e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean loginUser(String username, String password) {
    try {
      Connection connection = Database.getConnection();
      String hashedPassword = hashPassword(password);
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, hashedPassword);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();
    } catch (SQLException | NoSuchAlgorithmException e) {
      e.printStackTrace();
      return false;
    }
  }

  private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(password.getBytes());
    StringBuilder hexString = new StringBuilder();
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) hexString.append('0');
      hexString.append(hex);
    }
    return hexString.toString();
  }
}

