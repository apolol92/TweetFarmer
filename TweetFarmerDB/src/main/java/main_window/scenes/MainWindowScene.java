package main_window.scenes;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main_window.scenes.main_window_scene_panels.LeftPanel;
import main_window.scenes.main_window_scene_panels.RightPanel;

/**
 * Created by apolol92 on 05.05.2016.
 * This is the MainWindow Scene. It contains all views.
 */
public class MainWindowScene extends Scene{
    /**
     * This is the primary stage(the main window of the application)
     */
    private Stage primaryStage;
    /**
     * This is the layout of the MainWindowScene
     */
    private HBox hbLayout;
    /**
     * This is the lefside Panel
     */
    private LeftPanel leftPanel;
    /**
     * This is the rightside Panel
     */
    private RightPanel rightPanel;
    /**
     * This is the constant string for showing the title of the scene
     */
    private final String MAIN_WINDOW_SCENE_TITLE_STR = "TwitterFarmerDB - Farmer Overview";

    /**
     * This constructor creates the MainWindowScene
     * @param primaryStage, just the primary stage
     */
    public MainWindowScene(Stage primaryStage) {
        super(new HBox());
        this.hbLayout = (HBox)getRoot();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(this.MAIN_WINDOW_SCENE_TITLE_STR);
        this.leftPanel = new LeftPanel();
        this.rightPanel = new RightPanel();
        this.hbLayout.getChildren().addAll(this.leftPanel,this.rightPanel);
        this.primaryStage.setScene(this);
        this.primaryStage.show();
    }

}
