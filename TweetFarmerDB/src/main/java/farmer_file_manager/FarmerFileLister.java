package farmer_file_manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolol92 on 06.05.2016.
 */
public class FarmerFileLister extends FarmerFileManager{

    public FarmerFileLister() {
        super();
    }

    public ArrayList<String> listFiles() {
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
