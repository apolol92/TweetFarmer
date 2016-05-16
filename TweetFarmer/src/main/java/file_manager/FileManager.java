package file_manager;

import database.DatabaseConfigData;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by apolol92 on 13.05.2016.
 * Manages all local files
 */
public class FileManager {
    /**
     * Farmerspath
     */
    public static final String FARMERS_PATH = "farmers/";
    /**
     * Farmername
     */
    private final String farmername;

    /**
     * Constructor for FileManager
     * @param farmername
     */
    public FileManager(String farmername) {
        File farmerRootFolder = new File(this.FARMERS_PATH);
        if(!farmerRootFolder.exists()) {
            farmerRootFolder.mkdir();
        }
        this.farmername = farmername;
    }

    /**
     * Write farmer config
     * @param hashtags
     * @param databaseConfigData
     * @param classes
     * @param localStorage
     * @return
     */
    public boolean write_farmer(String hashtags[], DatabaseConfigData databaseConfigData, String classes[],boolean localStorage) {
        File farmerFolder = new File(this.FARMERS_PATH+this.farmername);
        if(!farmerFolder.exists()) {
            farmerFolder.mkdir();

            return new FarmerConfigWriter().write(farmername,hashtags,databaseConfigData,classes,localStorage);
        }
        else {
            return false;
        }
    }

    /**
     * Read farmer config
     * @param farmername
     * @return
     */
    public FarmerConfig readFarmer(String farmername) {
        File farmerFolder = new File(this.FARMERS_PATH+this.farmername);
        if(farmerFolder.exists()) {
            return new FarmerConfigReader().read(farmername);
        }
        else {
            return null;
        }
    }

    /**
     * This method collect all farmers
     * @return all farmers
    */
    public static ArrayList<String> listFarmers() {
        ArrayList<String> folders = new ArrayList<String>();
        File folder = new File(FARMERS_PATH);
        for(File f : folder.listFiles()) {
            if(f.isDirectory()) {
                folders.add(f.getName());
            }
        }
        return folders;

    }

}
