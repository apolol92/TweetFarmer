package farmer_file_manager;

import java.io.File;

/**
 * Created by apolol92 on 06.05.2016.
 * This is the parent class of all Farmer File Managers.
 */
public class FarmerFileManager {
    /**
     * This is the root-path of all farmers.
     * Should be like that "Program/farmers".
     */
    public final String FARMER_FOLDER = "farmers";

    /**
     * This constructor creates a FileManager.
     * It first check for the farmer-folder
     */
    public FarmerFileManager() {
        File farmerFolder = new File(FARMER_FOLDER);
        if (!farmerFolder.exists()) {
            farmerFolder.mkdir();
        }
    }
}
