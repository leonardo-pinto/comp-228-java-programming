package com.employees.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class App extends Application {

    Label labelId;
    Label labelName;
    Label labelDepartment;
    Label labelError;
    TextField textFieldId;
    TextField textFieldName;
    TextField textFieldDepartment;
    TableView<Employee> tableViewEmployee;

    private Connection connection;

    private void connect() {
        String databaseURL = "jdbc:mysql://localhost:3307/deloitte";
        String username = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(databaseURL, username, password);

            System.out.println("Connected to database");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private Boolean checkNameValidation(String name) {
        if(name.isBlank() || name.isEmpty()){
            labelError.setText("Please enter employee name");
            return false;
        }
        return true;
    }

    private Boolean checkDepartmentValidation(String department) {
        if(department.isBlank() || department.isEmpty()){
            labelError.setText("Please enter employee department");
            return false;
        }
        return true;
    }

    private Boolean checkIdValidation(String id) {
        if(id.isBlank() || id.isEmpty()){
            labelError.setText("Please enter employee id");
            return false;
        }
        return true;
    }

    private Boolean checkRecordExist(int id) {
        try {
            PreparedStatement selectWithWhereStatement = connection.prepareStatement("select * from employees where id=?");
            selectWithWhereStatement.setInt(1, id);
            ResultSet resultSet = selectWithWhereStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        labelError.setText("Employee ID is not available");
        return false;
    }

    private void updateEmployee() {
        try {
            String name = textFieldName.getText();
            String department = textFieldDepartment.getText();

            if(checkIdValidation(textFieldId.getText()) && checkNameValidation(textFieldName.getText()) && checkDepartmentValidation(textFieldDepartment.getText())) {
                int id = Integer.parseInt(textFieldId.getText());
                if(checkRecordExist(id)) {
                    PreparedStatement updateStatement = connection.prepareStatement("update employees set name=?, department=?, where id=?");
                    updateStatement.setString(1, name);
                    updateStatement.setString(2, department);
                    updateStatement.setInt(3, id);
                    updateStatement.executeUpdate();
                    System.out.println("Record updated");
                    clearAllFields();
                    loadEmployeesIntoTableView();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void loadEmployeesIntoTableView() {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        try {
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from employees");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                Employee employee = new Employee(id, name, department);

                employeeObservableList.add(employee);

                tableViewEmployee.setItems(employeeObservableList);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteEmployee() {
        try {
            if(checkIdValidation(textFieldId.getText())) {
                int id = Integer.parseInt(textFieldId.getText());
                if(checkRecordExist(id)) {
                    PreparedStatement deleteStatement = connection.prepareStatement("delete from employees where id=?");
                    deleteStatement.setInt(1, id);
                    deleteStatement.executeUpdate();
                    System.out.println("Deleted successfully");
                    clearAllFields();
                    loadEmployeesIntoTableView();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    private void insertEmployee() {
        try {
            String name = textFieldName.getText();
            String department = textFieldDepartment.getText();

            if (checkNameValidation(name) && checkDepartmentValidation(department)) {
                PreparedStatement insertStatement = connection.prepareStatement("insert into employees (name, department) values (?, ?)");
                insertStatement.setString(1, name);
                insertStatement.setString(2, department);
                insertStatement.executeUpdate();
                System.out.println("Record inserted");
                clearAllFields();
                loadEmployeesIntoTableView();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void clearAllFields() {
        textFieldId.clear();
        textFieldDepartment.clear();
        textFieldName.clear();
        labelError.setText("");
    }



    @Override
    public void start(Stage stage) throws Exception {

        connect();


        // Labels
        labelId = new Label("ID: ");
        labelName = new Label("Name: ");
        labelDepartment = new Label("Department");
        labelError = new Label();

        // Text fields
        textFieldId = new TextField();
        textFieldId.setPromptText("Enter ID");
        textFieldName = new TextField();
        textFieldName.setPromptText("Enter Name");
        textFieldDepartment = new TextField();
        textFieldDepartment.setPromptText("Enter Department");

        // Buttons
        Button buttonInsert = new Button("Insert Employee");
        Button buttonDelete = new Button("Delete Employee");
        Button buttonUpdate = new Button("Update Employee");

        buttonInsert.setOnAction(actionEvent -> insertEmployee());
        buttonDelete.setOnAction(actionEvent -> deleteEmployee());

        // Table View
        tableViewEmployee = new TableView<>();

        TableColumn<Employee, Integer> columnId = new TableColumn<>("ID");
        TableColumn<Employee, String> columnName = new TableColumn<>("Name");
        TableColumn<Employee, String> columnDepartment = new TableColumn<>("Department");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));

        tableViewEmployee.getColumns().addAll(columnId, columnName, columnDepartment);

        // Border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(labelError);

        // HBox
        HBox hBox = new HBox(20, buttonInsert, buttonUpdate, buttonDelete);
        hBox.setPadding(new Insets(10));

        // Grid panes
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(labelId, 0, 0);
        gridPane.add(labelName, 0, 1);
        gridPane.add(labelDepartment, 0, 2);
        gridPane.add(textFieldId, 1, 0);
        gridPane.add(textFieldName, 1, 1);
        gridPane.add(textFieldDepartment, 1, 2);

        VBox root = new VBox(10, gridPane, hBox, borderPane, tableViewEmployee);

        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("Employee Management");
        stage.setScene(scene);
        stage.show();

        loadEmployeesIntoTableView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
