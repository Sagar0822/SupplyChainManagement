package com.example.supplychainmanagementsahar17dec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    public TableView<Product> productTable;

    public Pane getAllProducts(){
        //create table at UI
        TableColumn id = new TableColumn("Id"); //Id show on UI
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"OnePlus", 35987));
//        data.add(new Product(1, "Samsung", 25987));
        ObservableList<Product> products = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(supplyChain.width, supplyChain.height);  //set table size as per bodyPain
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //remove extra column or space from table

        Pane tablePane = new Pane();
       // tablePane.setMinSize(supplyChain.width, supplyChain.height);
       // tablePane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.getChildren().add(productTable);
        return tablePane;

    }
    //Similar to getAllProduct method
    public Pane getProductsByName(String productName){
        //create table at UI
        TableColumn id = new TableColumn("Id"); //Id show on UI
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"OnePlus", 35987));
//        data.add(new Product(1, "Samsung", 25987));
        ObservableList<Product> products = Product.getProductsByName(productName);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(supplyChain.width, supplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        // tablePane.setMinSize(supplyChain.width, supplyChain.height);
        // tablePane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    //For buyNow Button
    public Product getSelectedProduct(){
        try{
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
