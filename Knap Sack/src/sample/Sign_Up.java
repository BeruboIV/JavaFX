package sample;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;

public class Sign_Up {
    static Stage window;
    static boolean answer;
    public static void display(){
        window = new Stage();
        window.setTitle("Sign Up");
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(8);

        //UserName must be Unique
        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);
        TextField passUsername = new TextField();
        GridPane.setConstraints(passUsername,1,0);

        //Name
        Label name = new Label("Name");
        GridPane.setConstraints(name,0,1);
        TextField passName = new TextField();
        GridPane.setConstraints(passName,1,1);

        //Gender
        Label gender = new Label("GENDER: ");
        GridPane.setConstraints(gender,0,4);

        ChoiceBox<String> passGender = new ChoiceBox<String>();
        //passGender.setScaleX(0.8);
        //passGender.setScaleY(0.8);
        passGender.getItems().addAll("Male","Female","Other");
        passGender.getSelectionModel().select(0);   //Predefined display -> Display 'Male'
        GridPane.setConstraints(passGender,1,4);

        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,3);
        TextField passPassword = new TextField();
        GridPane.setConstraints(passPassword,1,3);

        //Age
        Label age = new Label("Age: ");
        GridPane.setConstraints(age,0,5);
        ChoiceBox<Integer> passAge = new ChoiceBox<Integer>();
        for(int i = 18; i < 120; i++){
            passAge.getItems().addAll(i);
        }
        passAge.getSelectionModel().select(0);  //Will display Age : 18
        GridPane.setConstraints(passAge,1,5);

        Button submitButton = new Button("SUBMIT");
        GridPane.setConstraints(submitButton,1,6);
        submitButton.setOnAction(e -> {
            answer = Register.display(passUsername,passName,passPassword);
            if(answer) {
                window.close();
                System.out.println("Successfully Registered");
            }
        });

        layout.getChildren().addAll(name,passName,gender,passGender,submitButton,passUsername,username,password,passPassword,age,passAge);
        Scene scene = new Scene(layout,300,300);
        window.setScene(scene);
        window.show();
    }
    //Testing function for printing the choices in ChoiceBox
    public static void print(TextField text, ChoiceBox<String> choice){
        System.out.println(text.getText());
        System.out.println(choice.getValue());
    }
}
