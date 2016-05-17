package local_storage;

import custom_tweet.Tweet;
import file_manager.FileManager;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by apolol92 on 16.05.2016.
 * This LocalStorager write and read local stored tweets
 */
public class LocalStorager {
    /**
     * Insert new Tweet
     * @param farmername
     * @param tweet
     */
    public static void insertTweet(String farmername, Tweet tweet) {
        File inputFile = new File(FileManager.FARMERS_PATH + farmername + "/tweets.xml");
        if(inputFile.exists()) {
            addTweet(farmername, tweet);
        }
        else {
            createTweetFile(farmername, tweet);
        }
    }

    /**
     * Create file and insert tweet
     * @param farmername
     * @param tweet
     */
    private static void createTweetFile(String farmername, Tweet tweet) {
        try {
            Element tweetsNode = new Element("tweets");
            Element tNode = new Element("tweet");
            //ID Element
            Element idElement = new Element("Id");
            idElement.setText(tweet.getId()+"");
            tNode.addContent(idElement);
            //Username Element
            Element usernameElement = new Element("Username");
            usernameElement.setText(tweet.getUsername());
            tNode.addContent(usernameElement);
            //Screenname Element
            Element screennameElement = new Element("Screenname");
            screennameElement.setText(tweet.getScreenname());
            tNode.addContent(screennameElement);
            //Date
            Element dateElement = new Element("Date");
            dateElement.setText(tweet.getDate());
            tNode.addContent(dateElement);
            //Text
            Element textElement = new Element("TweetText");
            textElement.setText(tweet.getText());
            tNode.addContent(textElement);
            //Retweets
            Element retweetsElement = new Element("Retweets");
            retweetsElement.setText(tweet.getRetweets()+"");
            tNode.addContent(retweetsElement);
            //Likes
            Element likesElement = new Element("Likes");
            likesElement.setText(tweet.getLikes()+"");
            tNode.addContent(likesElement);
            //Classes
            Element classElement = new Element("Class");
            classElement.setText(tweet.getCl());
            tNode.addContent(classElement);
            tweetsNode.addContent(tNode);
            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();
            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(tweetsNode, new FileWriter(FileManager.FARMERS_PATH + farmername + "/tweets.xml"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add tweet to file
     * @param farmername
     * @param tweet
     */
    private static void addTweet(String farmername, Tweet tweet) {
        try {
            File inputFile = new File(FileManager.FARMERS_PATH + farmername + "/tweets.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element rootElement = document.getRootElement();
            Element tNode = new Element("tweet");
            //ID Element
            Element idElement = new Element("Id");
            idElement.setText(tweet.getId()+"");
            tNode.addContent(idElement);
            //Username Element
            Element usernameElement = new Element("Username");
            usernameElement.setText(tweet.getUsername());
            tNode.addContent(usernameElement);
            //Screenname Element
            Element screennameElement = new Element("Screenname");
            screennameElement.setText(tweet.getScreenname());
            tNode.addContent(screennameElement);
            //Date
            Element dateElement = new Element("Date");
            dateElement.setText(tweet.getDate());
            tNode.addContent(dateElement);
            //Text
            Element textElement = new Element("TweetText");
            textElement.setText(tweet.getText());
            tNode.addContent(textElement);
            //Retweets
            Element retweetsElement = new Element("Retweets");
            retweetsElement.setText(tweet.getRetweets()+"");
            tNode.addContent(retweetsElement);
            //Likes
            Element likesElement = new Element("Likes");
            likesElement.setText(tweet.getLikes()+"");
            tNode.addContent(likesElement);
            //Classes
            Element classElement = new Element("Class");
            classElement.setText(tweet.getCl());
            tNode.addContent(classElement);
            rootElement.addContent(tNode);
            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();
            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(rootElement, new FileWriter(FileManager.FARMERS_PATH + farmername + "/tweets.xml"));

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Read all tweets from local file system
     * @param farmername
     * @return
     */
    public static ArrayList<Tweet> readAllTweetsFromLocal(String farmername) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            File inputFile = new File(FileManager.FARMERS_PATH+farmername+"/tweets.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            org.jdom2.Document document = saxBuilder.build(inputFile);
            org.jdom2.Element tweetsElement = document.getRootElement();
            for(int i = 0; i < tweetsElement.getChildren().size(); i++) {
                long id = Long.parseLong(tweetsElement.getChildren().get(i).getChildren().get(0).getText());
                String username = tweetsElement.getChildren().get(i).getChildren().get(1).getText();
                String screenname = tweetsElement.getChildren().get(i).getChildren().get(2).getText();
                String date = tweetsElement.getChildren().get(i).getChildren().get(3).getText();
                String tweetText = tweetsElement.getChildren().get(i).getChildren().get(4).getText();
                int retweets = Integer.parseInt(tweetsElement.getChildren().get(i).getChildren().get(5).getText());
                int likes = Integer.parseInt(tweetsElement.getChildren().get(i).getChildren().get(6).getText());
                String cl = tweetsElement.getChildren().get(i).getChildren().get(7).getText();
                System.out.println(cl);
                tweets.add(new Tweet(cl,id,username,screenname,date,tweetText,retweets,likes));
            }

         }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return tweets;
    }
}
