package custom_twitter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by apolol92 on 07.05.2016.
 * This class contains a single tweet.
 * Tweet-ID, Tweet-Text, perhaps a class(if it was classified before)
 */
public class Tweet {
    /**
     * Tweet-ID
     */
    public long id;
    /**
     * Tweet-Text
     */
    public String text;
    /**
     * Class
     */
    public String cl;
    /**
     * Constructs a Tweet with
     * @param id
     * @param text
     */
    public Tweet(long id, String text) {
        this.id = id;
        this.text = text;

    }

    /**
     * Constructs a Tweet with
     * @param id
     * @param text
     * @param cl
     */
    public Tweet(long id, String text, String cl) {
        this.id = id;
        this.text = text;
        this.cl = cl;
    }

    public Tweet(long id) {
        this.id = id;
    }

    /**
     * Get the lowest tweet id in list
     * @param tweets
     * @return
     */
    public static long getMinTweetId(ArrayList<Tweet> tweets) {
        if(tweets.size()>0) {
            long min = tweets.get(0).id;
            for (int i = 0; i < tweets.size(); i++) {
                if(tweets.get(i).id<min) {
                    min = i;
                }
            }
            return min;
        }
        return -1;
    }

    public static boolean containsId(ArrayList<Tweet> tweets, long id) {
        System.out.println(tweets.size());
        for(int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).id==id) {
                return true;
            }
        }
        return false;
    }
}
