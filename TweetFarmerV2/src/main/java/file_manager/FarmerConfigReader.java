package file_manager;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;

/**
 * Created by apolol92 on 16.05.2016.
 */
public class FarmerConfigReader {

    public FarmerConfig read(String farmername) {
        try {
            FarmerConfig farmerConfig = new FarmerConfig();
            File inputFile = new File(FileManager.FARMERS_PATH+farmername+"/main_config.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            org.jdom2.Document document = saxBuilder.build(inputFile);

            Element classElement = document.getRootElement();
            List<Element> nodes = classElement.getChildren();

            farmerConfig.setName(document.getRootElement().getAttribute("name").getValue());
            int i = 0;
            for (Element node : nodes) {
                switch(i) {
                    case 0:
                        //Hashtags
                        List<Element> supNodes = node.getChildren();
                        String[] hts = new String[node.getChildren().size()];
                        for(int d = 0; d < supNodes.size(); d++) {
                            hts[d] = supNodes.get(d).getAttribute("str").getValue();
                        }
                        farmerConfig.setHashtags(hts);
                        break;
                    case 1:
                        String value = node.getAttribute("str").getValue();
                        if(value=="1") {
                            farmerConfig.setLocalStorage(true);
                        }
                        else {
                            farmerConfig.setLocalStorage(false);
                        }
                        break;
                    case 2:
                        //Classes
                        List<Element> supNodes4 = node.getChildren();
                        String[] hts2 = new String[node.getChildren().size()];
                        for(int d = 0; d < supNodes4.size(); d++) {
                            hts2[d] = supNodes4.get(d).getAttribute("str").getValue();
                            System.out.println(hts2[d]);
                        }
                        farmerConfig.setClasses(hts2);
                        break;
                    case 3:
                        String v = node.getAttribute("str").getValue();
                        if(v=="1") {
                            farmerConfig.setDatabaseStorage(true);
                        }
                        else {
                            farmerConfig.setDatabaseStorage(false);
                        }
                        break;
                }
                i++;

            }
            return farmerConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
