module com.example.leonardopinto_comp228testfall2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.leonardopinto_comp228testfall2023 to javafx.fxml;
    exports com.example.leonardopinto_comp228testfall2023;
}