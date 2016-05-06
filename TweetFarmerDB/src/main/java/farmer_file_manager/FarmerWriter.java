package farmer_file_manager;


import java.io.File;

/**
 * Created by apolol92 on 06.05.2016.
 * With this class you write your Farmers in your filesystem.
 */
public class FarmerWriter extends FarmerFileManager {

    /**
     * This constructor creates a FarmerWriter.
     */
    public FarmerWriter() {
        super();    //Checks if their is a folder for farmers available
    }

    /**
     * This method makes the farmer persistend in file system..
     * @param farmerFileConfigData, config data
     * @return true, if farmer didn't exist before.
     */
    public boolean writeFarmer(FarmerFileConfigData farmerFileConfigData) {
        File farmerFolder = new File(super.FARMER_FOLDER+"/"+farmerFileConfigData.getName());
        if (!farmerFolder.exists()) {
            farmerFolder.mkdir();
            File farmerConfigFolder = new File(super.FARMER_FOLDER+"/config");
            farmerConfigFolder.mkdir();
            File farmerOutputFolder = new File(super.FARMER_FOLDER+"/output");
            farmerOutputFolder.mkdir();
            farmerFileConfigData.writeInFolder(super.FARMER_FOLDER+"/config/main_config.xml");
            return true;
        }
        else {
            return false;
        }
    }


}
