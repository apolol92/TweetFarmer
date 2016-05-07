package farmer_file_manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolol92 on 06.05.2016.
 * This class lists all farmers in farmer-folder
 */
public class FarmerFileLister extends FarmerFileManager{

    /**
     * Constructor + Super-Constructor, which check if folder available
     */
    public FarmerFileLister() {
        super();
    }

    /**
     * This method collect all farmers
     * @return all farmers
     */
    public ArrayList<String> listFarmers() {
        ArrayList<String> folders = new ArrayList<String>();
        File folder = new File(super.FARMER_FOLDER);
        for(File f : folder.listFiles()) {
            if(f.isDirectory()) {
                folders.add(f.getName());
            }
        }
        return folders;

    }
}
