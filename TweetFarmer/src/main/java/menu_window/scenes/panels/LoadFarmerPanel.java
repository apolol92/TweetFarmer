package menu_window.scenes.panels;

import farmer_window.FarmerWindow;
import global.Global;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by apolol92 on 10.05.2016.
 * List and Load Farmers
 */
public class LoadFarmerPanel extends VBox{
    /**
     * Button for Starting/Loading Farmer
     */
    private Button btStartFarmer;
    /**
     * ListView which contains all Farmers
     */
    public ListView<String> lvFarmers;
    /**
     * Just the Headline of LoadFarmerPanel
     */
    private Label lbFarmers;

    /**
     * Creates the Panel
     */
    public LoadFarmerPanel() {
        super();
        this.lbFarmers = new Label("Farmers");
        this.lbFarmers.setFont(new Font(Global.FONT_FAMILY,24));
        this.lvFarmers = new ListView<String>();
        this.btStartFarmer = new Button("Load Farmer");
        this.btStartFarmer.setFont(new Font(Global.FONT_FAMILY,Global.NORMAL_TEXT_SIZE));
        this.btStartFarmer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO: Farmer Window
                FarmerWindow farmerWindow = new FarmerWindow();
            }
        });
        this.getChildren().addAll(this.lbFarmers, this.lvFarmers,this.btStartFarmer);
    }
}
