import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the NGOAids class that contains all the methods to manipulate the aids received by an NGO.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class NGOAids {
    private String aid;
    private int quantity;
    private NGO user;

    /**
     * Creates an NGOAids with aid and user set to null and quantity set to zero (0).
     */
    public NGOAids() {} 
    /**
     * Creates an NGOAids with the speicified aid, quantity, and user.
     * 
     * @param aid the content of aid
     * @param quantity the value of quantity
     * @param user the NGO user
     */
    public NGOAids(String aid, int quantity, NGO user) {
        this.aid = aid;
        this.quantity = quantity;
        this.user = user;
    }
    /**
     * Returns the amount of aids the current NGO logged in has received so far.
     * 
     * @param user the user logged
     * @return the amount of aids the current NGO has received
     */
    public static int aidsReceivedCount(NGO user) {
        ArrayList<String> aidsList = NGOAids.getFilteredAidsList(user, "aidsList.csv");

        return aidsList.size();
    }
    /**
     * Writes the NGOAids to the aidsNeeded.csv files.
     * If the file is unable to be written to, the user will receive an error message.
     */
    public void writeAidstoFile() {
        try {
            Writer aidsFile = new FileWriter("aidsNeeded.csv", true);
            aidsFile.write(this.toCSVString());
            aidsFile.close();
            System.out.println("Input successfully added.");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load data.");
            System.out.println("Please verify that you have the file 'aidsNeeded.csv'");
        }
    }
    /**
     * To be used with the getAidsNeeded and getFilteredAidsNeeded methods.
     * This method inputs the given string into a suitable format to be output to the user.
     * 
     * @param input the desired aids string to be output to the user
     * @return a string that is appropriately formatted to be output to the user
     */
    private static String aidsNeededtoString(String[] input) {
        String NGOname = input[2];
        NGO currentNGO = new NGO();
        currentNGO = NGOAccountHandler.getUser(NGOname);

        NGOAids currentAids = new NGOAids(input[0], Integer.valueOf(input[1]), currentNGO);

        return currentAids.toString();
    }
    /**
     * To be used with the getAidsList and getFilteredAidsList methods.
     * This method inputs the given string into a suitable format to be output to the user.
     * 
     * @param input the desired aids string to be output to the user
     * @return a string that is appropriately formatted to be output to the user
     */
    private static String getAidstoString(String[] input) {

        String NGOname = input[4];
        NGO currentNGO = new NGO();
        
        currentNGO = NGOAccountHandler.getUser(NGOname);
        NGOAids currentAids = new NGOAids(input[2], Integer.valueOf(input[3]), currentNGO);
        NGODonor currentDonor = new NGODonor(input[0], input[1]);

        return currentDonor.toString() + " " + currentAids.toString();
    }
    /**
     * Retrieves every entry from a file containing all of the aids received/needed by all the NGOs.
     * 
     * @param filename the specified filename the method will retrieve data from
     * @return an ArrayList containing of all the aids received/needed by NGOs
     */
    public static ArrayList<String> getAidsList(String filename) {
        ArrayList<String> aidsList = new ArrayList<>();

        try {
            List<String> list =  Files.readAllLines(Paths.get(filename));
            if (filename.equals("aidsList.csv")) {
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    aidsList.add(getAidstoString(data));
                }
            }
            else
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    aidsList.add(aidsNeededtoString(data));
                }
            Collections.sort(aidsList);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load data.");
            System.out.println("Please verify that you have the file '" + filename + ".csv'");
        }

        return aidsList;
    }
    /**
     * Retrieves all the aids received or needed by a specified NGO from files containing the requested information
     * 
     * @param NGOFilter user inputs the specific NGO who's aids they wish to view
     * @param filename the name of the file the method will read from
     * @return an ArrayList of aids received/needed by the specified NGO
     */
    public static ArrayList<String> getFilteredAidsList(NGO NGOFilter, String filename) {
        ArrayList<String> aidsList = new ArrayList<>();

        try {
            List<String> list =  Files.readAllLines(Paths.get(filename));
            if (filename.equals("aidsList.csv")) {
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    NGO currentNGO = new NGO();
                    currentNGO = NGOAccountHandler.getUser(data[4]);
                    if (currentNGO.compareTo(NGOFilter) > 0)
                        aidsList.add(getAidstoString(data));
                }
            }
            else {
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    NGO currentNGO = new NGO();
                    currentNGO = NGOAccountHandler.getUser(data[2]);
                    if (currentNGO.compareTo(NGOFilter) > 0)
                        aidsList.add(aidsNeededtoString(data));
                }
            }
            Collections.sort(aidsList);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load data.");
            System.out.println("Please verify that you have the file '" + filename + ".csv'");
        }
        return aidsList;
    }
    /**
     * This remove's an NGO's aids needed data from the files.
     * 
     * @param account the account whose aids data is to be removed.
     */
    protected static void removeFromFile(NGO account) {
        ArrayList<String> aidsNeededList = new ArrayList<>();
        ArrayList<String> newAidsNeededList = new ArrayList<>();

        aidsNeededList = getAidsList("aidsNeeded.csv");

        for (String i : aidsNeededList)
            if (i.contains(account.getNGOName()) == false)
                newAidsNeededList.add(i);
                
        try {
            Writer AidsNeededFile = new FileWriter("aidsNeeded.csv", false);
            for (String i : newAidsNeededList) {
                String[] items = i.split(" ");
                String entry = items[0] + "," + items[1] + "," + items[2] + "," + items[3] + "\n";
                AidsNeededFile.write(entry);
            }
            AidsNeededFile.close();

        } catch (IOException e) {
            System.out.println("ERROR: Unable to write to file.");
            System.out.println("'Please try again.");
        }
    }
    /**
     * Returns the aid, quantity, the NGOName, and the NGO manpower
     * 
     * @return a string of aid, quantity, the NGOName, and the NGO manpower in a suitable format for a CSV file
     */
    public String toCSVString() {
        return this.aid + "," + this.quantity + "," + this.user.getNGOName() + "," + this.user.getManpower() + "\n";
    }
    /**
     * Returns the content of aid, quantity, the NGOName, and the NGO manpower
     * 
     * @return returns a string of the content of aid, the value of quantity, the content of NGOName, and the value of manpower in an appropriate string format
     */
    @Override
    public String toString() {
        return this.aid + " " + this.quantity + " " + this.user.getNGOName() + " " + this.user.getManpower();
    }

}