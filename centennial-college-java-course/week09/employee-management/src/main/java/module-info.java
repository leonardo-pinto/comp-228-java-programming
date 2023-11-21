module com.employees.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.employees.app to javafx.fxml;
    exports com.employees.app;
}