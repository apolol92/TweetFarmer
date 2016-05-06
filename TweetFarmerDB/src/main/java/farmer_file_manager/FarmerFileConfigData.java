package farmer_file_manager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

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
    String TwitterAccessTokenSecret;
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
        return TwitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
        TwitterAccessTokenSecret = twitterAccessTokenSecret;
    }


    /**
     * This method write this Data in a file
     * @param folder, in which folder?
     */
    public void writeInFolder(String folder) {
        try {
            Document document = DocumentHelper.createDocument();
            //Farmer-Name
            Element root = document.addElement("farmer").addAttribute("name",this.getName());
            //Hashtags
            Element hashtagsElement = document.addElement("hashtags");
            for(String h : this.getHashtags()) {
                Element hashtagElement = hashtagsElement.addElement("hashtag").addAttribute("str",h);
            }
            //Database
            Element databaseElement = document.addElement("database");
            Element databaseIpElement = databaseElement.addElement("ip").addAttribute("str",this.getDatabaseip());
            Element databasename = databaseElement.addElement("name").addAttribute("str",this.getDatabasename());
            Element databaseUsername = databaseElement.addElement("username").addAttribute("str",this.getDatabaseUsername());
            Element databasePassword = databaseElement.addElement("password").addAttribute("str",this.getDatabasePassword());
            Element databaseTyp = databaseElement.addElement("databaseTyp").addAttribute("str",this.getDatabaseTyp());
            //Twitter
            Element twitterElement = document.addElement("twitter");
            Element twitterAccessToken = twitterElement.addElement("access_token").addAttribute("str",this.getTwitterAccessToken());
            Element twitterAccessTokenSecret = twitterElement.addElement("access_token_secret").addAttribute("str",this.getTwitterAccessTokenSecret());
            //Classes
            Element classesElement = document.addElement("classes");
            for(String c : this.getClasses()) {
                Element classElement = classesElement.addElement("class").addAttribute("str", c);
            }
            //XML-Writer
            FileWriter out = new FileWriter(folder);
            document.write( out );

        }catch (Exception ex) {

        }
    }
}
