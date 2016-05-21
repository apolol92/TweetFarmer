package sql;

import custom_tweet.Tweet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by apolol92 on 20.05.2016.
 */
public class TweetConverter {
    public static ArrayList<Tweet> getTweetsFromDatabase(ResultSet rs, String farmername) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            while(rs.next()) {
                //farmer_classes.name, farmer_tweets.tweet_text, farmer_tweets.likes
                //farmer_tweets.retweets, tweetid
                tweets.add(new Tweet(rs.getString(1),rs.getLong(5),"none","none","none",rs.getString(2),rs.getInt(4),
                        rs.getInt(3)));
            }
            return tweets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
