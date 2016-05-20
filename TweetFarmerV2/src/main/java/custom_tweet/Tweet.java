package custom_tweet;
import java.util.ArrayList;

/**
 * Created by apolol92 on 07.05.2016.
 * This class contains a single tweet.
 * Tweet-ID, Tweet-Text, perhaps a class(if it was classified before)
 */
public class Tweet {
    public String getProfilImageUrl() {
        return profilImageUrl;
    }

    public void setProfilImageUrl(String profilImageUrl) {
        this.profilImageUrl = profilImageUrl;
    }

    /**
     * URL to profile image..
     */
    private String profilImageUrl;
    /**
     * Screenname
     */
    private String screenname;
    /**
     * Username
     */
    private String username;
    /**
     * Creation time
     */
    private String date;

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Amount of likes
     */
    private int likes;
    /**
     * Amount of retweets
     */
    private int retweets;
    /**
     * Tweet-ID
     */
    private long id;
    /**
     * Tweet-Text
     */
    private String text;
    /**
     * Class
     */
    private String cl;
    /**
     * Constructs a Tweet with
     * @param id
     * @param text
     */
    public Tweet(long id, String username, String screenname, String date, String text, int retweets, int likes, String profilImageUrl) {
        this.id = id;
        this.username = username;
        this.screenname = screenname;
        this.date = date;
        this.text = text;
        this.retweets = retweets;
        this.likes = likes;
        this.profilImageUrl = profilImageUrl;
    }

    public Tweet(String cl, long id, String username, String screenname, String date, String text, int retweets, int likes) {
        this.id = id;
        this.username = username;
        this.screenname = screenname;
        this.date = date;
        this.text = text;
        this.retweets = retweets;
        this.likes = likes;
        this.cl = cl;
    }

    public Tweet(long id, String username, String screenname, String date, String text, int retweets, int likes) {
        this.id = id;
        this.username = username;
        this.screenname = screenname;
        this.date = date;
        this.text = text;
        this.retweets = retweets;
        this.likes = likes;
    }

    public Tweet(Tweet t) {
        this.id = t.id;
        this.username = t.getUsername();
        this.screenname = t.getScreenname();
        this.date = t.getDate();
        this.text = t.text;
        this.cl = t.cl;
        this.retweets = t.retweets;
        this.likes = t.likes;
        this.profilImageUrl = t.profilImageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
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
                    min = tweets.get(i).id;
                }
            }
            return min;
        }
        return Long.MAX_VALUE;
    }

    public static boolean containsId(ArrayList<Tweet> tweets, long id) {
        if(tweets.size()==0) {
            return false;
        }
        for(int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).id==id) {
                return true;
            }
        }
        return false;
    }
}
