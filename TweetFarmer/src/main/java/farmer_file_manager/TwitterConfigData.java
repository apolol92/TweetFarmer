package farmer_file_manager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by apolol92 on 09.05.2016.
 * This class contains all Twitter-Config-Data.
 * AccessToken, ConsumerKey..
 */
public class TwitterConfigData {

    /**
     * Singleton-Instance
     */
    private static TwitterConfigData twitterConfigData = null;

    /**
     * Twitter-Consumer-Key
     */
    private String twitterConsumerKey;
    /**
     * Twitter-Consumer-Secret
     */
    private String twitterConsumerSecret;
    /**
     * Twitter-Access-Token
     */
    private String twitterAccessToken;
    /**
     * Twitter-Access-Token-Secret
     */
    private String twitterAccessTokenSecret;

    public static void setTwitterConfigData(TwitterConfigData twitterConfigData) {
        TwitterConfigData.twitterConfigData = twitterConfigData;
    }

    public String getTwitterTwitterConsumerKey() {
        return twitterConsumerKey;
    }


    public String getTwitterTwitterConsumerSecret() {
        return twitterConsumerSecret;
    }


    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }


    public String getTwitterAccessTokenSecret() {
        return twitterAccessTokenSecret;
    }

    public void setTwitterConsumerKey(String twitterTwitterConsumerKey) {
        this.twitterConsumerKey = twitterTwitterConsumerKey;
    }

    public void setTwitterConsumerSecret(String twitterTwitterConsumerSecret) {
        this.twitterConsumerSecret = twitterTwitterConsumerSecret;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }

    /**
     * Path to the global config file
     */
    public static final String TWITTER_CONFIG_PATH = "twitter_config.xml";

    /**
     * Default-Constructor
     */
    protected  TwitterConfigData() {

    }

    /**
     * Get TwitterConfigData
     * @return
     */
    public static TwitterConfigData getTwitterConfigData() {
        if(twitterConfigData==null) {
            twitterConfigData = new TwitterConfigData();
        }
        readTwitterConfigData();
        return twitterConfigData;
    }


    /**
     * Read the twitter config data
     */
    private static void readTwitterConfigData() {
        try {
            File inputFile = new File(TWITTER_CONFIG_PATH);
            SAXBuilder saxBuilder = new SAXBuilder();
            org.jdom2.Document document = saxBuilder.build(inputFile);
            org.jdom2.Element classElement = document.getRootElement();
            List<Element> nodes = classElement.getChildren();
            int i = 0;
            for (org.jdom2.Element node : nodes) {
                switch(i) {
                    case 0:
                        twitterConfigData.setTwitterConsumerKey(node.getAttribute("str").getValue());
                        break;
                    case 1:
                        twitterConfigData.setTwitterConsumerSecret(node.getAttribute("str").getValue());
                        break;
                    case 2:
                        twitterConfigData.setTwitterAccessToken(node.getAttribute("str").getValue());
                        break;
                    case 3:
                        twitterConfigData.setTwitterAccessTokenSecret(node.getAttribute("str").getValue());
                        break;
                }
                i++;
            }


        }
        catch(Exception ex) {

        }
    }

    /**
     * Exists twitter config file?
     * @return true, if exists
     */
    public static boolean fileExists() {
        File f = new File(TWITTER_CONFIG_PATH);
        if(f.exists()) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Write Twitter Config Data
     * @param twitterConsumerKey
     * @param twitterConsumerSecret
     * @param twitterAccessToken
     * @param twitterAccessTokenSecret
     */
    public static void writeTwitterConfigData(String twitterConsumerKey, String twitterConsumerSecret, String twitterAccessToken, String twitterAccessTokenSecret) {
        try {
            Document document = DocumentHelper.createDocument();
            //Farmer-Name
            org.dom4j.Element root = document.addElement("twitter");
            //Twitter
            org.dom4j.Element elTwitterConsumerKey = root.addElement("consumer_key").addAttribute("str",twitterConsumerKey);
            org.dom4j.Element elTwitterConsumerSecret = root.addElement("consumer_secret").addAttribute("str",twitterConsumerSecret);
            org.dom4j.Element elTwitterAccessToken = root.addElement("access_token").addAttribute("str",twitterAccessToken);
            org.dom4j.Element elTwitterAccessTokenSecret = root.addElement("access_token_secret").addAttribute("str",twitterAccessTokenSecret);
            //XML-System.out
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter( System.out, format );
            writer.write( document );
            //File output
            FileOutputStream fos = new FileOutputStream(TWITTER_CONFIG_PATH);
            XMLWriter writerFos = new XMLWriter(fos, format);
            writerFos.write(document);
            writerFos.flush();
        }
        catch(Exception ex) {

        }
    }

}
