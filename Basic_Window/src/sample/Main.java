package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<Products> table;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("thenewboston - JavaFX");


        //Name column
        TableColumn<Products,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Products,Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<Products,Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn,priceColumn,quantityColumn);

        VBox layout = new VBox();
        layout.getChildren().addAll(table);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    //Get all of the products
    public ObservableList<Products> getProduct(){
        ObservableList<Products> products = FXCollections.observableArrayList();
        products.add(new Products("Laptop",859.00,20));
        products.add(new Products("Notebook",1.49,30));
        products.add(new Products("Toilet",99.00,10));
        products.add(new Products("Apples",.50,23));
        return products;
    }
}







