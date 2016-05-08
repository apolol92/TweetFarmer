package farmer_window.scenes.farmer_windw_scene_panels;

import custom_twitter.Tweet;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class LeftPanel extends VBox{
    int DATA_SIZE = 1000;
    int data[] = new int[DATA_SIZE];
    int group[] = new int[10];

    public LeftPanel(ArrayList<Tweet> dbTweets) {
        prepareData();
        groupData();

        Label labelInfo = new Label();
        labelInfo.setText("Classified Tweets");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart =
                new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);

        xAxis.setLabel("Classes");
        yAxis.setLabel("Amount");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Histogram");
        series1.getData().add(new XYChart.Data("Class1", group[0]));
        series1.getData().add(new XYChart.Data("Class2", group[1]));

        barChart.getData().addAll(series1);


        this.getChildren().addAll(labelInfo, barChart);


    }

    //generate dummy random data
    private void prepareData(){

        Random random = new Random();
        for(int i=0; i<DATA_SIZE; i++){
            data[i] = random.nextInt(100);
        }
    }

    //count data population in groups
    private void groupData(){
        for(int i=0; i<10; i++){
            group[i]=0;
        }
        for(int i=0; i<DATA_SIZE; i++){
            if(data[i]<=10){
                group[0]++;
            }else if(data[i]<=20){
                group[1]++;
            }else if(data[i]<=30){
                group[2]++;
            }else if(data[i]<=40){
                group[3]++;
            }else if(data[i]<=50){
                group[4]++;
            }else if(data[i]<=60){
                group[5]++;
            }else if(data[i]<=70){
                group[6]++;
            }else if(data[i]<=80){
                group[7]++;
            }else if(data[i]<=90){
                group[8]++;
            }else if(data[i]<=100){
                group[9]++;
            }
        }
    }
}
