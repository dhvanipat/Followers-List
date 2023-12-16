import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* java removeEveryOtherLine followersFile followingFile
 * 
 * 
 */

public class main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("ERROR: Please enter 2 args\n");
            return;
        }

        ArrayList<String> followers = importFile(args[0]);
        ArrayList<String> following = importFile(args[1]);
        
        ArrayList<String> allKnown = mergeLists(followers, following);

        ArrayList<String> mutuals = new ArrayList<String>();
        ArrayList<String> i_follow_but_dont_follow_me  = new ArrayList<String>();
        ArrayList<String> follows_me_but_i_dont_follow  = new ArrayList<String>();
        ArrayList<String> other  = new ArrayList<String>();

        for (int i = 0; i < allKnown.size(); i++) {
            String currUser = allKnown.get(i);

            if (followers.contains(currUser)) {
                if (following.contains(currUser)) {
                    mutuals.add(currUser);
                } else {
                    follows_me_but_i_dont_follow.add(currUser);
                }
            } else {
                if (following.contains(currUser)) {
                    i_follow_but_dont_follow_me.add(currUser);
                } else {
                    other.add(currUser);
                }
            }
        }

        System.out.println("Mutuals:");
        printList(mutuals);
        System.out.println();
        System.out.println("People who follow me but I don't follow:");
        printList(follows_me_but_i_dont_follow);
        System.out.println();
        System.out.println("People who I follow but don't follow me:");
        printList(i_follow_but_dont_follow_me);
        System.out.println();
        System.out.println("Neither:");
        printList(other);
        System.out.println();
    }

    private static ArrayList<String> importFile(String fileName) {
        ArrayList<String> list = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int counter = 0;
            while (true) {
                String line = null;

                try {
                    line = reader.readLine();
                } catch (IOException f) {
                    System.err.println("ERROR: With buffered reader readLine");
                    return null;
                }

                if (line == null) {
                    break;
                } else if (counter % 2 == 0) {
                    list.add(line);
                }

                counter++;
            }

            return list;

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: With initializing file input stream");
            return null;
        }
    }

    private static ArrayList<String> mergeLists(ArrayList<String> a1, ArrayList<String> a2) {
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < a1.size(); i++) {
            if (!list.contains(a1.get(i))) {
                list.add(a1.get(i));
            }
        }

        for (int i = 0; i < a2.size(); i++) {
            if (!list.contains(a2.get(i))) {
                list.add(a2.get(i));
            }
        }

        return list;
    }

    private static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + list.get(i));
        }
    }
}
