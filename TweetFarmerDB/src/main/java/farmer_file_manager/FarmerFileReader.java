package farmer_file_manager;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by apolol92 on 06.05.2016.
 * This class reads farmer from farmer-folder.
 */
public class FarmerFileReader extends FarmerFileManager{

    /**
     * This is the FarmerFileReader constructor.
     * It checks if farmer-folder exists.. if not it will create this farmer-folder.
     */
    public FarmerFileReader() {
        super();
    }

    /**
     * This method reads the farmer config file.
     * @param farmerName, just the farmer name
     * @return farmerFileConfigData
     */
    public FarmerFileConfigData readFarmerConfig(String farmerName) {
        String farmerConfigPath = this.FARMER_FOLDER+"/"+farmerName+"/config/main_config.xml";
        FarmerFileConfigData farmerFileConfigData = new FarmerFileConfigData();
        File farmerFolder = new File(farmerConfigPath);
        if (farmerFolder.exists()) {
            return farmerFileConfigData.readeFromFolder(farmerConfigPath);
        }
        else {
            return null;
        }
    }
}
