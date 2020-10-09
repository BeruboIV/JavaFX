package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    static boolean answer;
    public static boolean display(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Pane layout = new Pane();
        Label label = new Label();
        label.setText("Are you sure you want to quit?");
        label.setTranslateX(30);
        label.setTranslateY(5);
        label.setFont(Font.font(15));
        layout.getChildren().add(label);


        //Creating buttons for exit
        Button yesButton, noButton;
        yesButton = new Button("YES");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        yesButton.setTranslateX(50);
        yesButton.setTranslateY(30);
        noButton = new Button("NO");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        noButton.setTranslateX(110);
        noButton.setTranslateY(30);
        layout.getChildren().addAll(yesButton,noButton);

        Scene scene = new Scene(layout,250,100);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
