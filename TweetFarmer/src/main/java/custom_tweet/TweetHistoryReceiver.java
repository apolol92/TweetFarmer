package custom_tweet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolol92 on 08.05.2016.
 * This class collects historical tweets.
 * It will automatically go back in history.
 * It checks the oldest tweet and just get older tweets.
 */
public class TweetHistoryReceiver {
    //Twitter-Configuration
    /**
     * Twitter consumer key
     */
    private String consumerKey;
    /**
     * Twitter consumer secret
     */
    private String consumerSecret;
    /**
     * Twitter access token
     */
    private String accessToken;
    /**
     * Twitter access token secret
     */
    private String accessTokenSecret;
    /**
     * Twitter-Factory
     */
    TwitterFactory twitterFactoy;
    /**
     * Hashtags
     */
    private final String[] hashtags;
    /**
     * Query-String
     */
    String queryString = "";
    /**
     * Historical Tweets, which are processed
     */
    ArrayList<Tweet> historyTweets;


    /**
     * Creates the TweetHistoryReceiver
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param accessTokenSecret
     * @param hashtags
     */
    public TweetHistoryReceiver(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret, String[] hashtags) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.hashtags = hashtags;
        this.historyTweets = new ArrayList<Tweet>();
        setup();
        queryStringSetup();
    }

    /**
     * Setup Twitter-Configuration
     */
    private void setup() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(this.consumerKey )
                .setOAuthConsumerSecret(this.consumerSecret)
                .setOAuthAccessToken(this.accessToken)
                .setOAuthAccessTokenSecret(this.accessTokenSecret);
        this.twitterFactoy = new TwitterFactory(cb.build());

    }

    /**
     * Query-String setup
     */
    private void queryStringSetup() {
        for(int i = 0; i < this.hashtags.length; i++) {
            if(i+1!=this.hashtags.length) {
                queryString += "(#" + this.hashtags[i] + ") OR";
            }
            else {
                queryString += "(#" + this.hashtags[i] + ")";
            }
        }
    }

    /**
     * Get new old tweets
     * @return
     */
    public ArrayList<Tweet> getNewTweets() {
        ArrayList<Tweet> nTweets = new ArrayList<Tweet>();
        long minId = Tweet.getMinTweetId(this.historyTweets);
        //Querying
        try {
            Twitter twitter = this.twitterFactoy.getInstance();
            Query query = new Query(this.queryString);
            query.setLang("de");
            query.setMaxId(minId-1);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    if(!Tweet.containsId(this.historyTweets,tweet.getId())) {
                        nTweets.add(new Tweet(tweet.getId(), tweet.getUser().getName(), tweet.getUser().getScreenName(), tweet.getUser().getCreatedAt().toString(), tweet.getText(),tweet.getRetweetCount(),tweet.getFavoriteCount(),tweet.getUser().getProfileImageURL()));
                        System.out.println(tweet.getId()+ ":" + tweet.getUser().getName()+":"+tweet.getText()+":"+tweet.getRetweetCount()+":"+tweet.getFavoriteCount());
                    }
                }
            } while ((query = result.nextQuery()) != null);
            //Copy tweets to history
            addNewTweets2History(nTweets);
            //Return new tweets
            return nTweets;

        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * Get the min tweet id from history tweets
     * @return
     */
    public long getMinTweetIdInHistory() {
        return Tweet.getMinTweetId(this.historyTweets);
    }

    /**
     * Add new tweets to history tweets
     */
    private void addNewTweets2History(ArrayList<Tweet> nTweets) {
        for(int i = 0; i < nTweets.size(); i++) {
            this.historyTweets.add(new Tweet(nTweets.get(i)));
        }
    }




}