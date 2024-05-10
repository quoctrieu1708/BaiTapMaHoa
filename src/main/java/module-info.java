module org.example.baitapmahoa {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  opens org.example.baitapmahoa to javafx.fxml;
  exports org.example.baitapmahoa;
}