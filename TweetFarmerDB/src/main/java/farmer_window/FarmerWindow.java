package farmer_window;

import farmer_window.scenes.FarmerScene;
import javafx.stage.Stage;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class FarmerWindow {
    public void create(String farmerName) {
        Stage stage = new Stage();
        FarmerScene farmerScene = new FarmerScene(stage,farmerName);


    }
}
