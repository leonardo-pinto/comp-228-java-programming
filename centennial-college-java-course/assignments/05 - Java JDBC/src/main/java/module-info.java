module com.example.leonardopintocomp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.leonardopintocomp228lab5 to javafx.fxml;
    exports com.example.leonardopintocomp228lab5;
}