package main;

import config_data.TwitterConfigData;
import javafx.application.Application;
import javafx.stage.Stage;
import menu_window.MenuWindow;
import setup_window.SetupWindow;
import setup_window.SetupWindowController;

/**
 * Created by apolol92 on 19.05.2016.
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
    public void start(Stage primaryStage) throws Exception {
        if(TwitterConfigData.fileExists()) {
            //Menu Window
            MenuWindow menuWindow = new MenuWindow();


        }
        else {
            //Setup Window
            SetupWindowController.firstStart = true;
            SetupWindow setupWindow = new SetupWindow();

        }
    }
}
