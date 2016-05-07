package custom_twitter;

import farmer_file_manager.FarmerFileConfigData;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by apolol92 on 07.05.2016.
 */
public class TweetCollector {

    private FarmerFileConfigData farmerFileConfigData;
    /**
     * TwitterFactory
     */
    TwitterFactory twitterFactory;

    /**
     * Old tweets
     */
    ArrayList<Tweet> dbTweets;
    public TweetCollector(FarmerFileConfigData farmerFileConfigData, ArrayList<Tweet> dbTweets) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(farmerFileConfigData.getTwitterConsumerKey())
                .setOAuthConsumerSecret(farmerFileConfigData.getTwitterConsumerSecret())
                .setOAuthAccessToken(farmerFileConfigData.getTwitterAccessToken())
                .setOAuthAccessTokenSecret(farmerFileConfigData.getTwitterAccessTokenSecret());
        this.twitterFactory = new TwitterFactory(cb.build());
        this.dbTweets = dbTweets;
        this.farmerFileConfigData = farmerFileConfigData;

    }

    public ArrayList<Tweet> getFirstTweets() {
        ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
        Random rnd = new Random();
        int r = rnd.nextInt(this.farmerFileConfigData.getHashtags().length);
        String tag = this.farmerFileConfigData.getHashtags()[r];
        Twitter twitter = this.twitterFactory.getInstance();
        Query query = new Query(tag);
        QueryResult result;
        try {
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                System.out.println("sdafadsfadsfasdfasdfadsfasdf " + this.dbTweets.size());
                for (Status tweet : tweets) {
                    if (!Tweet.containsId(this.dbTweets, tweet.getId())&&!Tweet.containsId(tweetList, tweet.getId())) {
                        System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                        tweetList.add(new Tweet(tweet.getId(), tweet.getText()));

                    }
                }
            }
            while(tweetList.size()<30);
            return tweetList;
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }

    }


}
