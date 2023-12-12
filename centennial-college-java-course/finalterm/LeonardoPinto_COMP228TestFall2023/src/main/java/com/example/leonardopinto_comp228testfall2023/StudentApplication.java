package com.example.leonardopinto_comp228testfall2023;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class StudentApplication extends Application {

    private Connection connection;
    Label cityLabel;
    TextField cityTextField;
    TableView<Student> tableViewStudents;

    private void connect() {
        String dbUrl = "jdbc:mysql://localhost:3306/studentapp";
        String userName = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("Connected to database!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void selectUserByCity() {
        try {
            String cityInput = cityTextField.getText();

            if (!cityInput.isBlank()) {
                PreparedStatement selectStatement = connection.prepareStatement("select * from Students where city=?");
                selectStatement.setString(1, cityInput);
                ResultSet resultSet = selectStatement.executeQuery();

                ObservableList<Student> studentObservableList = FXCollections.observableArrayList();

               while(resultSet.next()) {
                   int studentId = resultSet.getInt("studentID");
                   String firstName = resultSet.getString("firstName");
                   String lastName = resultSet.getString("lastName");
                   String address = resultSet.getString("address");
                   String city = resultSet.getString("city");
                   String province = resultSet.getString("province");
                   String postalCode = resultSet.getString("postalCode");

                    Student student = new Student(studentId, firstName, lastName, address, city, province, postalCode);
                    studentObservableList.add(student);
               }

               tableViewStudents.setItems(studentObservableList);
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();

        // Labels
        cityLabel = new Label("Enter City: ");

        // Text Fields
        cityTextField = new TextField();

        // Buttons
        Button searchButton = new Button("Search");
        searchButton.setOnAction(actionEvent -> selectUserByCity());

        // Table
        tableViewStudents = new TableView<>();

        TableColumn<Student, Integer> columnStudentId = new TableColumn<>("Student ID");
        TableColumn<Student, String> columnFirstName = new TableColumn<>("First Name");
        TableColumn<Student, String> columnLastName = new TableColumn<>("Last Name");
        TableColumn<Student, String> columnAddress = new TableColumn<>("Address");
        TableColumn<Student, String> columnCity = new TableColumn<>("City");
        TableColumn<Student, String> columnProvince = new TableColumn<>("Province");
        TableColumn<Student, String> columnPostalCode = new TableColumn<>("Postal Code");

        columnStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        columnProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        columnPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tableViewStudents.getColumns().addAll(columnStudentId, columnFirstName, columnLastName, columnAddress, columnCity, columnProvince, columnPostalCode);

        VBox root = new VBox(10, cityLabel, cityTextField, searchButton, tableViewStudents);
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 575, 400);

        stage.setTitle("Student App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
