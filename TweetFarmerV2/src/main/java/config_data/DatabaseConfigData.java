package config_data;

import file_manager.FileManager;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by apolol92 on 13.05.2016.
 * Contains all Database Config-Data to access a database
 */
public class DatabaseConfigData {
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDbTyp() {
        return dbTyp;
    }

    public void setDbTyp(String dbTyp) {
        this.dbTyp = dbTyp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Database-Ip
     */
    private String ip;
    /**
     * Database-Typ(POSTGRESQL/MYSQL/MSSQL/..)
     */
    private String dbTyp;
    /**
     * Database-Port
     */
    private String port;
    /**
     * Databasename
     */
    private String databasename;
    /**
     * Database-Username
     */
    private String username;
    /**
     * Dasbase-Password
     */
    private String password;

    /**
     * Default Constructor
     */
    public DatabaseConfigData() {

    }
    /**
     * Constructor
     * @param ip
     * @param dbTyp
     * @param port
     * @param databasename
     * @param username
     * @param password
     */
    public DatabaseConfigData(String ip, String dbTyp, String port, String databasename, String username, String password) {
        this.ip = ip;
        this.dbTyp = dbTyp;
        this.port = port;
        this.databasename = databasename;
        this.username = username;
        this.password = password;
    }

    public void writeData(String farmername) {
        try {
            Element tNode = new Element("database");
            Element classElement = new Element("database");
            tNode.addContent(new Element("ip").setText(ip));
            tNode.addContent(new Element("dbTyp").setText(dbTyp));
            tNode.addContent(new Element("port").setText(port+""));
            tNode.addContent(new Element("databasename").setText(databasename));
            tNode.addContent(new Element("username").setText(username));
            tNode.addContent(new Element("password").setText(password));
            //XMLOutputter
            XMLOutputter xmlOutput = new XMLOutputter();
            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(tNode, new FileWriter(FileManager.FARMERS_PATH + farmername + "/database_config.xml"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readData(String farmername) {
        try {
            File inputFile = new File(FileManager.FARMERS_PATH+farmername+"/database_config.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element tweetsElement = document.getRootElement();
            String ip = tweetsElement.getChildren().get(0).getText();
            String dbTyp = tweetsElement.getChildren().get(1).getText();
            String port = tweetsElement.getChildren().get(2).getText();
            String databasename = tweetsElement.getChildren().get(3).getText();
            String username = tweetsElement.getChildren().get(4).getText();
            String password = tweetsElement.getChildren().get(5).getText();
            this.ip = ip;
            this.dbTyp = dbTyp;
            this.port = port;
            this.databasename = databasename;
            this.username = username;
            this.password = password;


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
