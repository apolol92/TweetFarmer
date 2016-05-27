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
    private String language;
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

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * Hashtags
     */
    private String[] hashtags;
    /**
     * Query-String
     */
    String queryString = "";

    public ArrayList<Tweet> getHistoryTweets() {
        return historyTweets;
    }

    public void setHistoryTweets(ArrayList<Tweet> historyTweets) {
        this.historyTweets = historyTweets;
    }

    /**
     * Historical Tweets, which are processed
     */
    ArrayList<Tweet> historyTweets;


    /**
     * Creates the TweetHistoryReceiver
     *
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param accessTokenSecret
     * @param hashtags
     */
    public TweetHistoryReceiver(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret, String[] hashtags, String language) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.hashtags = hashtags;
        this.historyTweets = new ArrayList<Tweet>();
        this.language = language;
        setup();
        queryStringSetup();
    }

    public boolean containsTweet(long id) {
        for (int i = 0; i < historyTweets.size(); i++) {
            if (historyTweets.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Setup Twitter-Configuration
     */
    private void setup() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(this.consumerKey)
                .setOAuthConsumerSecret(this.consumerSecret)
                .setOAuthAccessToken(this.accessToken)
                .setOAuthAccessTokenSecret(this.accessTokenSecret);
        this.twitterFactoy = new TwitterFactory(cb.build());

    }

    /**
     * Query-String setup
     */
    private void queryStringSetup() {
        for (int i = 0; i < this.hashtags.length; i++) {
            if (i + 1 != this.hashtags.length) {
                queryString += "(#" + this.hashtags[i] + ") OR";
            } else {
                queryString += "(#" + this.hashtags[i] + ")";
            }
        }
    }

    /**
     * Get new old tweets
     *
     * @return
     */
    public ArrayList<Tweet> getNewTweets() {
        ArrayList<Tweet> nTweets = new ArrayList<Tweet>();
        long minId = Tweet.getMinTweetId(this.historyTweets);
        //Querying
        int counter = 0;
        try {
            do {
                counter++;
                Twitter twitter = this.twitterFactoy.getInstance();
                Query query = new Query(this.queryString);
                query.setLang(this.language);
                query.setMaxId(minId - 1);
                QueryResult result;
                do {
                    result = twitter.search(query);
                    List<Status> tweets = result.getTweets();
                    for (Status tweet : tweets) {
                        if (!containsId(this.historyTweets, tweet.getId())) {
                            nTweets.add(new Tweet(tweet.getId(), tweet.getUser().getName(), tweet.getUser().getScreenName(),
                                    tweet.getUser().getCreatedAt().toString(), tweet.getText(), tweet.getRetweetCount(),
                                    tweet.getFavoriteCount(), tweet.getUser().getBiggerProfileImageURL()));
                        }
                    }
                } while ((query = result.nextQuery()) != null);
                //Copy tweets to history
                addNewTweets2History(nTweets);
                System.out.println("Wait for it.." + counter);
                if (counter > 3) {
                    minId = getMaxTweetId(historyTweets) + counter*100;
                }
                if(counter>=5) {
                    break;
                }
            } while (nTweets.size() == 0);
            //Return new tweets
            return nTweets;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Get the min tweet id from history tweets
     *
     * @return
     */
    public long getMinTweetIdInHistory() {
        return Tweet.getMinTweetId(this.historyTweets);
    }

    /**
     * Add new tweets to history tweets
     */
    public void addNewTweets2History(ArrayList<Tweet> nTweets) {
        for (int i = 0; i < nTweets.size(); i++) {
            if (!containsTweet(nTweets.get(i).getId())) {
                this.historyTweets.add(new Tweet(nTweets.get(i)));
            }
        }
    }


    public void setLanguage(String language) {
        this.language = language;
    }
    /**
     * Get the max Tweet-ID
     * @param tweets
     * @return
     */
    public static long getMaxTweetId(ArrayList<Tweet> tweets) {
        if(tweets.size()>0) {
            long max = tweets.get(0).getId();
            for (int i = 0; i < tweets.size(); i++) {
                if(tweets.get(i).getId()>max) {
                    max = tweets.get(i).getId();
                }
            }
            return max;
        }
        return Long.MAX_VALUE;
    }

    /**
     * Do tweets contains id id?
     * @param tweets
     * @param id
     * @return
     */
    public static boolean containsId(ArrayList<Tweet> tweets, long id) {
        if(tweets.size()==0) {
            return false;
        }
        for(int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).getId()==id) {
                return true;
            }
        }
        return false;
    }
}