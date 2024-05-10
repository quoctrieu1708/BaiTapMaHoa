package org.example.baitapmahoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/btmahoa";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "170805";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }
}
