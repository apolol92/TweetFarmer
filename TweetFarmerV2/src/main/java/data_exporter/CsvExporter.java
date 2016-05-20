package data_exporter;

import au.com.bytecode.opencsv.CSVWriter;
import custom_tweet.Tweet;
import file_manager.FileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apolol92 on 17.05.2016.
 */
public class CsvExporter {

    public static void export(String farmername, ArrayList<Tweet> tweets) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(FileManager.FARMERS_PATH+farmername+"/output.csv"),',');
            System.out.println(tweets.size());
            for(int i = 0; i < tweets.size(); i++) {
                String[] cols = new String[4];
                cols[0] = tweets.get(i).getText();
                cols[1] = tweets.get(i).getLikes()+"";
                cols[2] = tweets.get(i).getRetweets()+"";
                cols[3] = tweets.get(i).getCl();
                System.out.println(tweets.get(i).getId());
                writer.writeNext(cols);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
