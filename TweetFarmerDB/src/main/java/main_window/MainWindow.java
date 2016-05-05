package main_window;

import javafx.application.Application;
import javafx.stage.Stage;
import main_window.scenes.MainWindowScene;

/**
 * Created by apolol92 on 05.05.2016.
 */
public class MainWindow extends Application {
    /**
     * This set the window width
     */
    public static final int WINDOW_WIDTH = 400;
    /**
     * This set the window height
     */
    public static final int WINDOW_HEIGHT = 500;

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
        /*
            First thing you have to do is to connect to the cellent-server.
            For this you have to crreate the LoginScene in the primaryStage.
         */
        MainWindowScene mainWindow = new MainWindowScene(primaryStage);


    }
}