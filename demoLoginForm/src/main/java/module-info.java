module com.example.demologinform {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.demologinform to javafx.fxml;
    exports com.example.demologinform;
}