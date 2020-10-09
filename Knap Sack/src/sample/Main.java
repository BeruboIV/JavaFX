package sample;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.management.Notification;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Stage window;
    boolean answer;
    String response2;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Intro Page");
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(8);
        layout.setHgap(10);

        Label userName = new Label("Username: ");
        GridPane.setConstraints(userName,0,0);

        TextField passUserName = new TextField();
        GridPane.setConstraints(passUserName,1,0);

        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,1);

        PasswordField passPassword = new PasswordField();
        passPassword.setPromptText("password");
        GridPane.setConstraints(passPassword,1,1);

        Text incorrectPassword = new Text();
        GridPane.setConstraints(incorrectPassword,2,1);

        Text incorrectUsername = new Text();
        GridPane.setConstraints(incorrectUsername,2,0);

        Button loginButton;
        loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String response = login(passUserName,passPassword);
            //System.out.println(response);
            if(response.equals("Login Successfull")){
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Sign In");
                tray.setMessage(response);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(50));
                //System.out.println(response);
                User_Profile.display(passUserName);
                stage.close();
            }
            if(response.equals("Username Does Not Exists")){

                incorrectUsername.setText(response);
                incorrectUsername.setFill(Color.RED);
            }else {
                if (response.equals("Incorrect Password")) {
                    incorrectPassword.setText(response);
                    incorrectPassword.setFill(Color.RED);
                }
            }
        });
        GridPane.setConstraints(loginButton,1,2);

        Label noAccount = new Label("Don't have an account?");
        noAccount.setTextFill(Color.BLUE);
        GridPane.setConstraints(noAccount,1,3);

        Button signupButton = new Button("Sign Up");
        GridPane.setConstraints(signupButton,1,4);

        //Sign-Up Functions:
        signupButton.setOnAction(e -> {
            Sign_Up.display();
        });
        layout.getChildren().addAll(userName,passUserName,incorrectUsername,loginButton,passPassword,password,incorrectPassword,noAccount,signupButton);
        //Creating an exit option
        stage.setOnCloseRequest(e -> {
            e.consume();
            answer = AlertBox.display();
            if(answer)
                stage.close();
        });
        stage.setScene(new Scene(layout,450,300));
        stage.show();
    }

    public String login(TextField username,TextField password){
        try{
        File file = new File("D:\\Java\\Knap Sack\\Database\\" + username.getText() + ".txt");
        if(file.exists()) {
            Scanner scanner = new Scanner(file);
            String pass = scanner.nextLine();   //Correct password present in Database
            if (pass.equals(password.getText()))
                response2 =  "Login Successfull";
            else
                response2 =  "Incorrect Password";
        }else{
            response2 =  "Username Does Not Exists";
        }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return response2;
    }
}