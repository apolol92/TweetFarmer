package file_manager;

import database.DatabaseConfigData;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom2.input.SAXBuilder;


import java.io.*;
import java.util.List;
/**
 * Created by apolol92 on 13.05.2016.
 * Writes the Config File of a Farmer.
 */
public class FarmerConfigWriter {

    public static final String MAIN_CONFIG_XML = "main_config.xml";

    public boolean write(String farmername, String hashtags[], DatabaseConfigData databaseConfigData, String classes[], boolean localStorage) {

        try
        {
            Document document = DocumentHelper.createDocument();
            //Farmer-Name
            Element root = document.addElement("farmer");
            Element nameElement = root.addAttribute("name", farmername);
            //Hashtags
            Element hashtagsElement = root.addElement("hashtags");
            for (String h : hashtags) {
                Element hashtagElement = hashtagsElement.addElement("hashtag").addAttribute("str", h);
            }
            //LocalStorage?
            if(localStorage) {
                Element databaseElement = root.addElement("localStorage").addAttribute("str","1");
            }
            else {
                Element databaseElement = root.addElement("localStorage").addAttribute("str","0");
            }
           //Classes
            Element classesElement = root.addElement("classes");
            for (String c : classes) {
                Element classElement = classesElement.addElement("class").addAttribute("str", c);
            }
            //Database
            if(databaseConfigData != null) {
                Element databaseElement = root.addElement("database").addAttribute("str", "1");
                Element databaseIpElement = databaseElement.addElement("ip").addAttribute("str", databaseConfigData.getIp());
                Element databasePort = databaseElement.addElement("port").addAttribute("str", databaseConfigData.getPort());
                Element databasename = databaseElement.addElement("name").addAttribute("str", databaseConfigData.getDatabasename());
                Element databaseUsername = databaseElement.addElement("username").addAttribute("str", databaseConfigData.getUsername());
                Element databasePassword = databaseElement.addElement("password").addAttribute("str", databaseConfigData.getPassword());
                Element databaseTyp = databaseElement.addElement("databaseTyp").addAttribute("str", databaseConfigData.getDbTyp());
            }
            else {
                Element databaseElement = root.addElement("database").addAttribute("str", "0");
            }
            //XML-System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter(System.out, format);
            writer.write(document);
            //File output
            FileOutputStream fos = new FileOutputStream(FileManager.FARMERS_PATH+farmername+"/"+ MAIN_CONFIG_XML);
            XMLWriter writerFos = new XMLWriter(fos, format);
            writerFos.write(document);
            writerFos.flush();
            return true;

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

}
