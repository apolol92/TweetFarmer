package farmer_window;

import farmer_window.scenes.FarmerScene;
import javafx.stage.Stage;

/**
 * Created by apolol92 on 11.05.2016.
 * Represents the farmer window.
 */
public class FarmerWindow {
    /**
     * Stage of Farmer
     */
    private Stage stage;

    /**
     * Constructor of FarmerWindow
     */
    public FarmerWindow() {
        this.stage = new Stage();
        FarmerScene farmerScene = new FarmerScene(stage);
    }
}
