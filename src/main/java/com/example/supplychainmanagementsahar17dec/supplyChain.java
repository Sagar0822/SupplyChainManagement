package com.example.supplychainmanagementsahar17dec;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class supplyChain extends Application {

     public static final int width = 700, height = 600, headerbar = 50, footerbar = 50;

     Pane bodyPane = new Pane();
     Login login = new Login();
     ProductDetails productDetails = new ProductDetails();

     Button globelLoginButton;
     Label customerEmailLabel = null;
     String customerEmail = null;

     private GridPane headerBar(){
         TextField searchText = new TextField();
         Button searchButton = new Button("Search");
         searchButton.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent actionEvent){
                 String productName = searchText.getText();

                 //clear body and put this new pane in the body
                 bodyPane.getChildren().clear();
                 bodyPane.getChildren().add(productDetails.getProductsByName(productName));
             }
         });
         globelLoginButton = new Button("Log In");
         globelLoginButton.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent actionEvent) {
                 bodyPane.getChildren().clear();  //press login button clear bodypane page & show login page
                 bodyPane.getChildren().add(loginPage());
             }
         });
         customerEmailLabel = new Label("Welcome user");
      //GridPane -> divide pane window in block pattern
         GridPane gridPane = new GridPane();
         //fix header size in X & Y
         gridPane.setMinSize(bodyPane.getMinWidth(), headerbar-10);
         //gap b/w two grids ex searchText & button verticle & horz
         gridPane.setVgap(5);
         gridPane.setHgap(5);

         //gridPane.setStyle("-fx-background-color: #C0C0C0");
         gridPane.setAlignment(Pos.CENTER);  //center

         gridPane.add(searchText, 0, 0);
         gridPane.add(searchButton, 1, 0);
         gridPane.add(globelLoginButton, 2, 0);
         gridPane.add(customerEmailLabel, 3, 0);

         return gridPane;
     }
    private GridPane loginPage(){

        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label("I am message");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
               String email = emailTextField.getText();
               String password = passwordField.getText();
               //messageLabel.setText(email + " $$ " + password);
                if(login.customerLogin(email,password)){   //if email password correct then go inside & show both message
                    messageLabel.setText("Login successful");
                    customerEmail = email;
                    globelLoginButton.setDisable(true);
                    customerEmailLabel.setText("User : " + customerEmail);
                    //After login show all products
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }
                else{
                    messageLabel.setText("Login Failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        //fix bodyPane size in X & Y
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);

        //first is X co-ordinate & second is Y co-ordinate
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField,1, 1);
        gridPane.add(loginButton,0, 2);
        gridPane.add(messageLabel,1, 2);

        return gridPane;
    }

    private GridPane footerBar(){

        Button addToCardButton = new Button("Add To Card");
        Button buyNowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                  Product selectedProduct = productDetails.getSelectedProduct();
                  if(Order.placeOrder(customerEmail, selectedProduct)){
                      messageLabel.setText("Ordered");
                  }
                  else{
                      messageLabel.setText("Order Failed");
                  }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), footerbar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(50);

        //gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);  //center
        gridPane.setTranslateY(headerbar + height + 10);

        gridPane.add(addToCardButton, 0, 0);
        gridPane.add(buyNowButton, 1, 0);
        gridPane.add(messageLabel, 2, 0);

        return gridPane;
    }

    private Pane createContent(){
        Pane root = new Pane();
        // set size whole window or pane
         root.setPrefSize(width, height + headerbar + footerbar); //03

        bodyPane.setMinSize(width, height);
        //root.getChildren().addAll(loginPage()); //02 shift loginpage into bodypane
        bodyPane.setTranslateY(headerbar);
       // bodyPane.getChildren().addAll(loginPage()); //05
        bodyPane.getChildren().addAll(productDetails.getAllProducts());  //Table in bodypane
        root.getChildren().addAll(headerBar(), bodyPane, footerBar()); //04

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());  //01
        stage.setTitle("supplyChain");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}