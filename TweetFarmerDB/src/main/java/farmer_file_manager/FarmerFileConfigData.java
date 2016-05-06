package farmer_file_manager;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


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
        twitterAccessTokenSecret = twitterAccessTokenSecret;
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
     * @param twitterAccessToken
     * @param twitterAccessTokenSecret
     */
    public FarmerFileConfigData(String name, String hashtags, String databaseip, String databasename, String databaseUsername, String databasePassword,
                                String databaseTyp, String classes, String twitterAccessToken, String twitterAccessTokenSecret) {
        this.name = name;
        this.hashtags = hashtags.split(",");
        this.databaseip = databaseip;
        this.databasename = databasename;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.databaseTyp = databaseTyp;
        this.classes = classes.split(",");
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
            Element databasename = databaseElement.addElement("name").addAttribute("str",this.getDatabasename());
            Element databaseUsername = databaseElement.addElement("username").addAttribute("str",this.getDatabaseUsername());
            Element databasePassword = databaseElement.addElement("password").addAttribute("str",this.getDatabasePassword());
            Element databaseTyp = databaseElement.addElement("databaseTyp").addAttribute("str",this.getDatabaseTyp());
            //Twitter
            Element twitterElement = root.addElement("twitter");
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
            SAXReader reader = new SAXReader();
            Document document = reader.read( inputFile );

            Element classElement = document.getRootElement();

            List<Node> nodes = document.selectNodes("/farmer" );
            int i = 0;
            for (Node node : nodes) {
                switch (i) {
                    case 0:
                        farmerFileConfigData.setName(node.valueOf("@str"));
                        break;
                    case 1:
                        //farmerFileConfigData.set(node.valueOf("@str"));
                        List<Node> hashtags = node.selectNodes("/hashtags");
                        String[] hts = new String[hashtags.size()];
                        int d = 0;
                        for(Node hNode : hashtags) {
                            hts[d] = hNode.valueOf("@str");
                            d++;
                        }
                        farmerFileConfigData.setHashtags(hts);
                        break;
                    case 2:
                        farmerFileConfigData.setDatabaseip(node.valueOf("@str"));
                        break;
                    case 3:
                        farmerFileConfigData.setDatabasename(node.valueOf("@str"));
                        break;
                    case 4:
                        farmerFileConfigData.setDatabaseUsername(node.valueOf("@str"));
                        break;
                    case 5:
                        farmerFileConfigData.setDatabasePassword(node.valueOf("@str"));
                        break;
                    case 6:
                        farmerFileConfigData.setDatabaseTyp(node.valueOf("@str"));
                        break;
                    case 7:
                        farmerFileConfigData.setTwitterAccessToken(node.valueOf("@str"));
                        break;
                    case 8:
                        farmerFileConfigData.setTwitterAccessTokenSecret(node.valueOf("@str"));
                        break;
                    case 9:
                        //farmerFileConfigData.setClasses(node.valueOf("@str"));
                        List<Node> classes = node.selectNodes("/hashtags");
                        String[] cs = new String[classes.size()];
                        int e = 0;
                        for(Node cNode : classes) {
                            cs[e] = cNode.valueOf("@str");
                            e++;
                        }
                        farmerFileConfigData.setClasses(cs);
                        break;
                }
                i++;
            }
            return farmerFileConfigData;
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
