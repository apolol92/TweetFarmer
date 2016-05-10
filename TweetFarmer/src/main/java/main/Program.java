package main;

import farmer_file_manager.TwitterConfigData;
import javafx.application.Application;
import javafx.stage.Stage;
import menu_window.MenuWindow;
import setup_window.SetupWindow;


/**
 * Created by apolol92 on 09.05.2016.
 * Application-Class
 */
public class Program extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    /**
     * Starts javafx ui
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if(TwitterConfigData.fileExists()) {
            //Menu Window
            MenuWindow menuWindow = new MenuWindow(primaryStage);
        }
        else {
            //Setup Window
            SetupWindow setupWindow = new SetupWindow(primaryStage);

        }



    }
}
