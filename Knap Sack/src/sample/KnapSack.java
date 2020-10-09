package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class KnapSack {
    static String name1;
    static int maxWeight ;
    static int[] profit;
    static int[] weight;
    static String[] name;
    public static void display(String user){
        Stage window = new Stage();
        Label maxWeightLabel = new Label("Weight: ");
        Button submitButton = new Button("SUBMIT");
        TextField passMaxWeight = new TextField();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.getChildren().addAll(maxWeightLabel,passMaxWeight,submitButton);

        VBox vbox = new VBox();

        //Related information to be put on the screen
        Text info = new Text("Welcome " + user + "\nPlease enter the desired weight to get the optimal profit");
        vbox.getChildren().addAll(info,hbox);

        name1 = user;
        //Maximum weight
        submitButton.setOnAction(e -> {
            maxWeight = Integer.parseInt(passMaxWeight.getText());
            calculateMaximum(name1);
        });

        //System.out.println(profitTable[count-1][m]);
        Scene scene = new Scene(vbox,450,300);
        window.setScene(scene);
        window.show();
    }

    static public void calculateMaximum(String user){
        name = new String[1000006];
        profit = new int[1000006];
        weight = new int[1000006];

        int count = 0;
        try{
            File file = new File("D:\\Java\\Knap Sack\\Database\\" + user + ".txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            scanner.nextLine();
            int i = 1;
            while(scanner.hasNextLine()){

                String[] data = scanner.nextLine().split(",");
                if(data[0].equals("") || data[0].equals(" "))
                    continue;
                name[i] = data[0];
                weight[i] = Integer.parseInt(data[1]);
                profit[i] = Integer.parseInt(data[2]);
                i++;
                count++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
//        count = count + 1;
//        maxWeight = maxWeight + 1
        //Starting the algorithm
        int[][] profitTable = new int[count+1][maxWeight+1];  //Profit Table
        for(int i = 0; i <= maxWeight; i++)
            profitTable[0][i] = 0;
        for(int j = 0; j <= count; j++)
            profitTable[j][0] = 0;

        for(int i = 1; i <= count; i++){
            for(int j = 1; j <= maxWeight; j++){
                if(weight[i] > j){
                    profitTable[i][j] = profitTable[i-1][j];
                }
                else{
                    profitTable[i][j] = Math.max(profit[i] + profitTable[i-1][(j - weight[i])],profitTable[i-1][j]);
                }
            }
        }
        System.out.println("Maximum Profit : " + profitTable[count][maxWeight]);

        //Printing the objects to be included
        System.out.println("The objects to be included are:");
        int res = profitTable[count][maxWeight];
        int w = maxWeight;
        for(int i = count; i > 0 && res > 0; i--){
            if(res == profitTable[i-1][w])
                continue;
            else{
                System.out.println(name[i]);
                res = res - profit[i];
                w = w - weight[i];
            }
        }
    }
}
