package setup_window;

import javafx.stage.Stage;
import setup_window.scenes.SetupScene;

/**
 * Created by apolol92 on 09.05.2016.
 * This is the Setup-Window, used for setting up Twitter Access
 */
public class SetupWindow {
    /**
     * Stage
     */
    private Stage stage;

    /**
     * SetupWindow Constructor
     * @param stage
     */
    public SetupWindow(Stage stage) {
        this.stage = stage;
        SetupScene setupScene = new SetupScene(this.stage);


    }
}
