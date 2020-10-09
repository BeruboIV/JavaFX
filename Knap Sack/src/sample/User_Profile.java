package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class User_Profile {
    static TableView<Product> table;
    static boolean answer;
    static TextField name;
    static TextField weight;
    static TextField profit;
    static String user;

    public static void display(TextField username){
        user = username.getText();
        Stage window = new Stage();
        window.setTitle("Welcome " + username.getText());

        TableColumn<Product,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.cellValueFactoryProperty().setValue(new PropertyValueFactory<>("name"));

        TableColumn<Product,Integer> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(100);
        weightColumn.cellValueFactoryProperty().setValue(new PropertyValueFactory<>("weight"));

        TableColumn<Product,Integer> profitColumn = new TableColumn<>("Profit");
        profitColumn.setMinWidth(100);
        profitColumn.cellValueFactoryProperty().setValue(new PropertyValueFactory<>("profit"));

        table = new TableView<>();
        table.setItems(getProduct(user));
        table.getColumns().addAll(nameColumn,weightColumn,profitColumn);

        //Adding the Editable Window
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        name = new TextField();
        name.setPromptText("Name");
        weight = new TextField();
        weight.setPromptText("Weight");
        profit = new TextField();
        profit.setPromptText("Profit");
        Button addButton = new Button("ADD");
        addButton.setOnAction(e -> {
            addButtonClicked(user);
        });
        Button deleteButton = new Button("DELETE");
        deleteButton.setOnAction(e -> {
            deleteButtonClicked(user);
        });
        Button nextButton = new Button("NEXT");
        nextButton.setOnAction(e -> {
            KnapSack.display(user);
            window.close();
        });
        hbox.getChildren().addAll(name,weight,profit,addButton,deleteButton,nextButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table,hbox);

        window.setOnCloseRequest(e -> {
            e.consume();
            answer = AlertBox.display();
            if(answer)
                window.close();
        });

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.show();
    }

    public static void addButtonClicked(String username){
        //Adding the data to the window
        Product product = new Product();
        product.setName(name.getText());
        product.setWeight(weight.getText());
        product.setProfit(profit.getText());
        table.getItems().add(product);

        //Adding the data to the user profile
        try {
            File file = new File("D:\\Java\\Knap Sack\\Database\\" + username + ".txt");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.append(name.getText());
            bw.append(",");
            bw.append(weight.getText());
            bw.append(",");
            bw.append(profit.getText());
            bw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        name.clear();
        weight.clear();
        profit.clear();
    }

    public static void deleteButtonClicked(String username){
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        String removeTerm1 = table.getSelectionModel().getSelectedItem().getName() + ",";
        String removeTerm2 = String.valueOf(table.getSelectionModel().getSelectedItem().getWeight()) + ",";
        String removeTerm3 = String.valueOf(table.getSelectionModel().getSelectedItem().getProfit());
        replaceSelected(username,removeTerm1, removeTerm2,removeTerm3);
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);

    }

    public static void replaceSelected(String username,String string1, String string2,String string3) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("D:\\Java\\Knap Sack\\Database\\" + username + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            //System.out.println(inputStr); // display the original file for debugging

            // logic to replace lines in the string (could use regex here to be generic)

            inputStr = inputStr.replace(string1 + string2 + string3, " ," + " ," + " ");


            // display the new file for debugging
            //System.out.println("----------------------------------\n" + inputStr);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("D:\\Java\\Knap Sack\\Database\\" + username + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    private static ObservableList<Product> getProduct(String user){
        ObservableList<Product> products = FXCollections.observableArrayList();
        //products.add(new Product("Suitcase",20,10));
        //Loading Saved Data
        try{
            File file = new File("D:\\Java\\Knap Sack\\Database\\" + user + ".txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            scanner.nextLine(); //To skip the first 2 lines which hold password and name

            while(scanner.hasNextLine()){
                String[] data = scanner.nextLine().split(",");
                if(data[0].equals("") || data[0].equals(" "))
                    continue;
                products.add(new Product(data[0],data[1],data[2]));
            }

            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return products;
    }
}
