package com.example.leonardopintocomp228lab5;

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

public class ProductManagementApp extends Application {

    private Connection connection;
    Label labelId;
    Label labelName;
    Label labelCompany;
    Label labelPrice;
    Label labelError;

    TextField textFieldId;
    TextField textFieldName;
    TextField textFieldCompany;
    TextField textFieldPrice;

    TableView<Product> tableViewProducts;


    private void connect() {
        String dbUrl = "jdbc:mysql://localhost:3306/productmanagement";
        String userName = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("Connected to database!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private Boolean validateTextInput(String value, String inputName) {
        if (value.isEmpty() || value.isBlank()) {
            labelError.setText("Please enter product " + inputName);
            return false;
        }
        return true;
    }

    private Boolean validatePriceInput(String priceString) {
        try {
            if(Double.parseDouble(priceString) <= 0) {
                labelError.setText("Price must be greater than 0");
                return false;
            }
            return true;
        } catch (Exception exception) {
            labelError.setText("Price can not be empty");
            return false;
        }
    }

    private Boolean validateExistingRecord(int id) {
        try {
            PreparedStatement selectWithWhereStatement = connection.prepareStatement("select * from products where id=?");
            selectWithWhereStatement.setInt(1, id);
            ResultSet resultSet = selectWithWhereStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        labelError.setText("Invalid product id");
        return false;
    }

    private void clearAllFields() {
        textFieldId.clear();
        textFieldName.clear();
        textFieldCompany.clear();
        textFieldPrice.clear();
    }

    private void insert() {
        try {
            String name = textFieldName.getText();
            String company = textFieldCompany.getText();

            if (validateTextInput(name, "name") && validateTextInput(company, "company") && validatePriceInput(textFieldPrice.getText())) {
                double price = Double.parseDouble(textFieldPrice.getText());

                PreparedStatement insertStatement = connection.prepareStatement("insert into products (name, company, price) values (?, ?, ?)");
                insertStatement.setString(1, name);
                insertStatement.setString(2, company);
                insertStatement.setDouble(3, price);
                insertStatement.executeUpdate();
                loadProductsIntoTableView();
                clearAllFields();
                System.out.println("Product inserted successfully!");
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void loadProductsIntoTableView() {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();

        try {
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from products");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String company = resultSet.getString("company");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, name, company, price);
                productObservableList.add(product);
            }

            tableViewProducts.setItems(productObservableList);

        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }


    private void delete() {
        try {
            if(validateTextInput(textFieldId.getText(), "id")) {
                int id = Integer.parseInt(textFieldId.getText());
                if(validateExistingRecord(id)) {
                    PreparedStatement deleteStatement = connection.prepareStatement("delete from products where id=?");
                    deleteStatement.setInt(1, id);
                    deleteStatement.executeUpdate();
                    loadProductsIntoTableView();
                    clearAllFields();
                    System.out.println("Product deleted successfully!");
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void update() {
        try {
            if (validateTextInput(textFieldId.getText(), "id")
                    && validateTextInput(textFieldName.getText(), "name")
                    && validateTextInput(textFieldCompany.getText(), "company")
                    && validatePriceInput(textFieldPrice.getText())) {
                int id = Integer.parseInt(textFieldId.getText());
                if (validateExistingRecord(id)) {
                    PreparedStatement updateStatement = connection.prepareStatement("update products set name=?, company=?, price=? where id=?");
                    updateStatement.setString(1, textFieldName.getText());
                    updateStatement.setString(2, textFieldCompany.getText());
                    updateStatement.setDouble(3, Double.parseDouble(textFieldPrice.getText()));
                    updateStatement.setInt(4, id);
                    updateStatement.executeUpdate();
                    loadProductsIntoTableView();
                    clearAllFields();
                    System.out.println("Product updated successfully!");
                }
            }
        } catch (SQLException exception) {
                exception.printStackTrace();
            }

    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();

        labelId = new Label("ID");
        labelName = new Label("Name");
        labelCompany = new Label("Company");
        labelPrice = new Label("Price");
        labelError = new Label();

        textFieldId = new TextField();
        textFieldId.setPromptText("Product ID");

        textFieldName = new TextField();
        textFieldName.setPromptText("Product Name");

        textFieldCompany = new TextField();
        textFieldCompany.setPromptText("Product Company");

        textFieldPrice = new TextField();
        textFieldPrice.setPromptText("Product Price");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.setPadding(new Insets(15));
        gridPane.add(labelId, 0, 0);
        gridPane.add(labelName, 0, 1);
        gridPane.add(labelCompany, 0, 2);
        gridPane.add(labelPrice, 0, 3);
        gridPane.add(textFieldId, 1, 0);
        gridPane.add(textFieldName, 1, 1);
        gridPane.add(textFieldCompany, 1, 2);
        gridPane.add(textFieldPrice, 1, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(labelError);

        Button buttonInsert = new Button("Insert Product");
        buttonInsert.setOnAction(actionEvent -> insert());
        Button buttonUpdate = new Button("Update Product");
        buttonUpdate.setOnAction(actionEvent -> update());
        Button buttonDelete = new Button("Delete Product");
        buttonDelete.setOnAction(actionEvent -> delete());

        HBox hBox = new HBox(20, buttonInsert, buttonUpdate, buttonDelete);
        hBox.setPadding(new Insets(15));

        tableViewProducts = new TableView<>();

        TableColumn<Product, Integer> columnId = new TableColumn<>("ID");
        TableColumn<Product, String> columnName = new TableColumn<>("Name");
        TableColumn<Product, String> columnCompany = new TableColumn<>("Company");
        TableColumn<Product, Double> columnPrice = new TableColumn<>("Price");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewProducts.getColumns().addAll(columnId, columnName, columnCompany, columnPrice);

        VBox root = new VBox(10, gridPane, hBox, labelError, tableViewProducts);

        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("Product Management");
        stage.setScene(scene);
        stage.show();

        loadProductsIntoTableView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
