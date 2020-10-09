package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class Register {
    static boolean answer;
    public static boolean display(TextField username,TextField name ,TextField password){
        try{
            File file = new File("D:\\Java\\Knap Sack\\Database\\" + username.getText() + ".txt");
            if(file.exists()){
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);    //To make it the current window and make the background windows useless
                window.setTitle("Error");
                VBox layout = new VBox();
                Label error_message = new Label("Username already exists!");
                Button okButton = new Button("OK");
                okButton.setOnAction(e -> window.close());
                layout.getChildren().addAll(error_message,okButton);
                layout.setAlignment(Pos.CENTER);
                Scene scene = new Scene(layout,200,150);
                window.setScene(scene);
                window.showAndWait();
                answer = false;
                //return answer;
            }else{
                file.createNewFile();
                FileWriter fr = new FileWriter(file,true);
                BufferedWriter br = new BufferedWriter(fr);
                br.append(password.getText());
                br.newLine();
                br.append(name.getText());
                br.close();
                fr.close();
                answer = true;
                //return answer;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return answer;
    }

}
