package tweet_farmer_window;

import java.util.ArrayList;

/**
 * Created by apolol92 on 20.05.2016.
 */
public class ClassIdPair {
    public String name;
    public int id;

    public ClassIdPair(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static int getIdByClassname(ArrayList<ClassIdPair> a, String classname) {
        for(int i = 0; i < a.size(); i++) {
            System.out.println("COMPARE: " + a.get(i).name + " " +classname);
            if(a.get(i).name.compareTo(classname)==0) {
                return a.get(i).id;
            }
        }
        return -1;
    }


}
