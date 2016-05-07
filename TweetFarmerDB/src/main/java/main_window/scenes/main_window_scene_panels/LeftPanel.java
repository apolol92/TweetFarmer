package main_window.scenes.main_window_scene_panels;

import farmer_file_manager.FarmerFileLister;
import farmer_window.FarmerWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Created by apolol92 on 05.05.2016.
 * This class represents the farmer-overview.
 * Here you will find all created farmers.
 * You can also start a farmer in this pane.
 */
public class LeftPanel extends VBox{
    /**
     * This label is the headline of the left panel
     */
    private Label lbFarmers;
    /**
     * This constant String contains the text of lbFarmers
     */
    private final String LB_FARMERS_STR = "Farmers";
    /**
     * This ListView contains all farmers
     */
    public ListView<String> lvFarmers;
    /**
     * This button will start a Farmer
     */
    Button btStartFarmer;

    /**
     * This constant String contains the text of btStartFarmer
     */
    private final String BT_START_FARMER_STR = "Start Farmer";

    /**
     * Constructor with initialization
     */
    public LeftPanel() {
        super();
        this.lbFarmers = new Label(this.LB_FARMERS_STR);
        this.lvFarmers = new ListView<String>();
        this.btStartFarmer = new Button(this.BT_START_FARMER_STR);
        this.btStartFarmer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FarmerWindow mFarmerWindow = new FarmerWindow();
                mFarmerWindow.create(lvFarmers.getSelectionModel().getSelectedItem());
            }
        });
        this.getChildren().addAll(this.lbFarmers,this.lvFarmers,this.btStartFarmer);
        this.update();
    }

    public void update() {
        ObservableList<String> items = FXCollections.observableArrayList (new FarmerFileLister().listFarmers());
        this.lvFarmers.setItems(items);
    }
}
