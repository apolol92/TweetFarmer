package farmer_file_manager;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom2.input.SAXBuilder;


import java.io.*;
import java.util.List;

/**
 * Created by apolol92 on 06.05.2016.
 * This class represents the farmer config data
 * Instances will be created after btCreate-Click in RightPanel and in Farmer-Edit.
 * It will be used for passing data between Farmer-Config-Files and Program.
 */
public class FarmerFileConfigData {
    /**
     * Farmer-Name
     */
    String name;
    /**
     * twitter-hashtags
     */
    String[] hashtags;
    /**
     * Database-IP
     */
    String databaseip;
        /**
     * Database-Port
     */
    String databasePort;
    /**
     * Databasename
     */
    String databasename;
    /**
     * Database-Username
     */
    String databaseUsername;
    /**
     * Database-Password
     */
    String databasePassword;
    /**
     * Database-Typ
     */
    String databaseTyp;

    /**
     * Twitter-Consumer-Key
     */
    String twitterConsumerKey;
    /**
     * Twitter-Consumer-Secret
     */
    String twitterConsumerSecret;
    /**
     * Twitter-Access-Token
     */
    String twitterAccessToken;
    /**
     * Twitter-Access-Token-Secret
     */
    String twitterAccessTokenSecret;
    /**
     * Classes for classifier
     */
    String[] classes;

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    public String getDatabaseip() {
        return databaseip;
    }

    public void setDatabaseip(String databaseip) {
        this.databaseip = databaseip;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseTyp() {
        return databaseTyp;
    }

    public void setDatabaseTyp(String databaseTyp) {
        this.databaseTyp = databaseTyp;
    }
    public String getTwitterConsumerKey() {
        return twitterConsumerKey;
    }

    public void setTwitterConsumerKey(String twitterConsumerKey) {
        this.twitterConsumerKey = twitterConsumerKey;
    }

    public String getTwitterConsumerSecret() {
        return twitterConsumerSecret;
    }

    public void setTwitterConsumerSecret(String twitterConsumerSecret) {
        this.twitterConsumerSecret = twitterConsumerSecret;
    }

    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterAccessTokenSecret() {
        return twitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }
    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    /**
     * Default Constructor
     */
    public FarmerFileConfigData() {

    }

    /**
     * Create FarmerFileConfigData from raw input..
     * @param name
     * @param hashtags
     * @param databaseip
     * @param databasename
     * @param databaseUsername
     * @param databasePassword
     * @param databaseTyp
     * @param classes
     * @param twitterTwitterConsumerKey
     * @param twitterTwitterConsumerSecret
     * @param twitterAccessToken
     * @param twitterAccessTokenSecret
     */
    public FarmerFileConfigData(String name, String hashtags, String databaseip, String databasePort, String databasename, String databaseUsername, String databasePassword,
                                String databaseTyp, String classes, String twitterTwitterConsumerKey,String twitterTwitterConsumerSecret, String twitterAccessToken, String twitterAccessTokenSecret) {
        this.name = name;
        this.hashtags = hashtags.split(",");
        this.databaseip = databaseip;
        this.databasePort = databasePort;
        this.databasename = databasename;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.databaseTyp = databaseTyp;
        this.classes = classes.split(",");
        this.twitterConsumerKey = twitterTwitterConsumerKey;
        this.twitterConsumerSecret = twitterTwitterConsumerSecret;
        this.twitterAccessToken = twitterAccessToken;
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }



    /**
     * This method writes this Data in a file
     * @param path, in which file
     */
    public void writeInFile(String path) {
        try {
            Document document = DocumentHelper.createDocument();
            //Farmer-Name
            Element root = document.addElement("farmer");
            Element nameElement = root.addAttribute("name",this.getName());
            //Hashtags
            Element hashtagsElement = root.addElement("hashtags");
            for(String h : this.getHashtags()) {
                Element hashtagElement = hashtagsElement.addElement("hashtag").addAttribute("str",h);
            }
            //Database
            Element databaseElement = root.addElement("database");
            Element databaseIpElement = databaseElement.addElement("ip").addAttribute("str",this.getDatabaseip());
            Element databasePort = databaseElement.addElement("port").addAttribute("str",this.getDatabasePort());
            Element databasename = databaseElement.addElement("name").addAttribute("str",this.getDatabasename());
            Element databaseUsername = databaseElement.addElement("username").addAttribute("str",this.getDatabaseUsername());
            Element databasePassword = databaseElement.addElement("password").addAttribute("str",this.getDatabasePassword());
            Element databaseTyp = databaseElement.addElement("databaseTyp").addAttribute("str",this.getDatabaseTyp());
            //Twitter
            Element twitterElement = root.addElement("twitter");
            Element twitterConsumerKey = twitterElement.addElement("consumer_key").addAttribute("str",this.getTwitterConsumerKey());
            Element twitterConsumerSecret = twitterElement.addElement("consumer_secret").addAttribute("str",this.getTwitterConsumerSecret());
            Element twitterAccessToken = twitterElement.addElement("access_token").addAttribute("str",this.getTwitterAccessToken());
            Element twitterAccessTokenSecret = twitterElement.addElement("access_token_secret").addAttribute("str",this.getTwitterAccessTokenSecret());
            //Classes
            Element classesElement = root.addElement("classes");
            for(String c : this.getClasses()) {
                Element classElement = classesElement.addElement("class").addAttribute("str", c);
            }
            //XML-System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter( System.out, format );
            writer.write( document );
            //File output
            FileOutputStream fos = new FileOutputStream(path);
            XMLWriter writerFos = new XMLWriter(fos, format);
            writerFos.write(document);
            writerFos.flush();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method reads data from a file
     */
    public FarmerFileConfigData readeFromFolder(String path) {
        try {
            FarmerFileConfigData farmerFileConfigData = new FarmerFileConfigData();
            File inputFile = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            org.jdom2.Document document = saxBuilder.build(inputFile);

            org.jdom2.Element classElement = document.getRootElement();
            List<org.jdom2.Element> nodes = classElement.getChildren();

            farmerFileConfigData.setName(document.getRootElement().getAttribute("name").getValue());
            int i = 0;
            for (org.jdom2.Element node : nodes) {
                switch(i) {
                    case 0:
                        //Hashtags
                        List<org.jdom2.Element> supNodes = node.getChildren();
                        String[] hts = new String[node.getChildren().size()];
                        for(int d = 0; d < supNodes.size(); d++) {
                            hts[d] = supNodes.get(d).getAttribute("str").getValue();
                        }
                        farmerFileConfigData.setHashtags(hts);
                        break;
                    case 1:
                        //Database
                        List<org.jdom2.Element> supNodes2 = node.getChildren();
                        farmerFileConfigData.setDatabaseip(supNodes2.get(0).getAttribute("str").getValue());
                        farmerFileConfigData.setDatabasePort(supNodes2.get(1).getAttribute("str").getValue());
                        farmerFileConfigData.setDatabasename(supNodes2.get(2).getAttribute("str").getValue());
                        farmerFileConfigData.setDatabaseUsername(supNodes2.get(3).getAttribute("str").getValue());
                        farmerFileConfigData.setDatabasePassword(supNodes2.get(4).getAttribute("str").getValue());
                        farmerFileConfigData.setDatabaseTyp(supNodes2.get(5).getAttribute("str").getValue());
                        break;
                    case 2:
                        //Twitter
                        List<org.jdom2.Element> supNodes3 = node.getChildren();
                        farmerFileConfigData.setTwitterConsumerKey(supNodes3.get(0).getAttribute("str").getValue());
                        farmerFileConfigData.setTwitterConsumerSecret(supNodes3.get(1).getAttribute("str").getValue());
                        farmerFileConfigData.setTwitterAccessToken(supNodes3.get(2).getAttribute("str").getValue());
                        farmerFileConfigData.setTwitterAccessTokenSecret(supNodes3.get(3).getAttribute("str").getValue());
                        break;
                    case 3:
                        //Classes
                        List<org.jdom2.Element> supNodes4 = node.getChildren();
                        String[] hts2 = new String[node.getChildren().size()];
                        for(int d = 0; d < supNodes4.size(); d++) {
                            hts2[d] = supNodes4.get(d).getAttribute("str").getValue();
                            System.out.println(hts2[d]);
                        }
                        farmerFileConfigData.setClasses(hts2);
                        break;
                }
                i++;

            }
            return farmerFileConfigData;
        } catch (Exception e) {
            System.out.println("FEEEEEEHLER!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            return null;
        }
    }


}
