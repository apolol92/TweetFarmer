package menu_window.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import menu_window.scenes.panels.LoadFarmerPanel;
import menu_window.scenes.panels.NewFarmerPanel;

/**
 * Created by apolol92 on 10.05.2016.
 * MenuScene which contains LoadFarmerPanel and NewFarmerPanel
 */
public class MenuScene extends Scene {
    /**
     * HorizontalBOx for all Panels
     */
    private HBox hbLayout;
    /**
     * Stage
     */
    private Stage stage;
    /**
     * LoadFarmerPanel
     */
    LoadFarmerPanel loadFarmerPanel;
    /**
     * New Farmer Panel
     */
    NewFarmerPanel newFarmerPanel;

    public MenuScene(Stage stage) {
        super(new HBox());
        this.hbLayout = (HBox)getRoot();
        this.hbLayout.setPadding(new Insets(10,10,10,10));
        this.loadFarmerPanel = new LoadFarmerPanel();
        this.newFarmerPanel = new NewFarmerPanel();
        this.hbLayout.getChildren().addAll(loadFarmerPanel,newFarmerPanel);
        this.stage = stage;
        this.stage.setTitle("TweetFarmer - Menu");
        this.stage.setScene(this);
        this.stage.show();
    }


}
