module com.example.supplychainmanagementsahar17dec {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainmanagementsahar17dec to javafx.fxml;
    exports com.example.supplychainmanagementsahar17dec;
}