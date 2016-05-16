package file_manager;

/**
 * Created by apolol92 on 16.05.2016.
 */
public class FarmerConfig {
    /**
     * Farmer-Name
     */
    String name;
    /**
     * twitter-hashtags
     */
    String[] hashtags;
    /**
     * Classes for classifier
     */
    String[] classes;
    private boolean localStorage;
    private boolean databaseStorage;

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

    public String[] getClasses() {
        return classes;
    }

    public void setClasses(String[] classes) {
        this.classes = classes;
    }

    public void setLocalStorage(boolean localStorage) {
        this.localStorage = localStorage;
    }

    public boolean isLocalStorage() {
        return localStorage;
    }

    public void setDatabaseStorage(boolean databaseStorage) {
        this.databaseStorage = databaseStorage;
    }

    public boolean isDatabaseStorage() {
        return databaseStorage;
    }
}
