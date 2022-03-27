import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This is the DonorAids class that contains all the methods to manipulate the aids to be donated by donors.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class DonorAids {
    private Donor user;
    private String aid;
    private int quantity;
    /**
     * Creates an DonorAids with aid and user set to null and quantity set to zero (0).
     */
    public DonorAids() {} 
    /**
     * Creates an DonorAids with the speicified donor, aid(s) donated, and the quantity of the aid
     * 
     * @param aid the content of aid
     * @param quantity the value of quantity
     * @param user the donor donating
     */
    public DonorAids(Donor user, String aid, int quantity) {
        this.user = user;
        this.aid = aid;
        this.quantity = quantity;
    }
    /**
     * Writes the DonorAids to the aidsDonated.csv files.
     * If the file is unable to be written to, the user will receive an error message.
     * 
     * @param input the specified DonorAids to be written into the file
     */
    public static void writeAidstoFile(DonorAids input) {
        try {
            Writer aidsFile = new FileWriter("aidsDonated.csv", true);
            aidsFile.write(input.toCSVString());
            aidsFile.close();
            System.out.println("Input successfully added.");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load file. Please try again.");
        }
    }
    /**
     * To be used with the getAidsDonated and getFilteredaidsDonated methods.
     * This method inputs the given string into a suitable format to be output to the user.
     * 
     * @param input the desired aids string to be output to the user
     * @return a string that is appropriately formatted to be output to the user
     */
    private static String aidsDonatedtoString(String[] input) {
        String DonorID = input[0];
        Donor currentDonor = new Donor();
        currentDonor = DonorAccountHandler.getUser(DonorID);

        DonorAids currentAids = new DonorAids(currentDonor, input[2], Integer.valueOf(input[3]));

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

        String DonorID = input[0];
        Donor currentDonor = new Donor();
        currentDonor = DonorAccountHandler.getUser(DonorID);

        DonorAids currentAids = new DonorAids(currentDonor, input[2], Integer.valueOf(input[3]));
        NGO currentNGO = NGOAccountHandler.getUser(input[4]);

        return currentAids.toString() + " " + currentNGO.toStringNGO();
    }
    /**
     * Retrieves every entry from a file containing all of the aids donated by every Donor.
     * 
     * @param filename the specified filename the method will retrieve data from
     * @return an ArrayList containing of all the aids donated by the Donors
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
                    aidsList.add(aidsDonatedtoString(data));
                }
            Collections.sort(aidsList);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load file. Please try again");
        }

        return aidsList;
    }
    /**
     * Retrieves all the aids donated by a specified Donor from files containing the requested information
     *
     * @param DonorFilter user inputs the specific Donor whos donations they wish to view
     * @param filename the name of the file the method will read from
     * @return an ArrayList of aids donated by the specified Donor
     */
    public static ArrayList<String> getFilteredAidsList(Donor DonorFilter, String filename) {
        ArrayList<String> aidsList = new ArrayList<>();

        try {
            List<String> list =  Files.readAllLines(Paths.get(filename));
            if (filename.equals("aidsList.csv")) {
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    Donor currentDonor = new Donor();
                    currentDonor = DonorAccountHandler.getUser(data[0]);
                    if (currentDonor.compareTo(DonorFilter) > 0)
                        aidsList.add(getAidstoString(data));
                }
            }
            else {
                for (int i = 0; i < list.size(); i++) {
                    String[] data = list.get(i).split(",");
                    Donor currentDonor = new Donor();
                    currentDonor = DonorAccountHandler.getUser(data[0]);
                    if (currentDonor.compareTo(DonorFilter) > 0)
                        aidsList.add(aidsDonatedtoString(data));
                }
            }
            Collections.sort(aidsList);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load file. Please try again");
        }
        return aidsList;
    }
    /**
     * Returns the aid, quantity, the DonorID, and the Donor manpower
     * 
     * @return a string of aid, quantity, the DonorID, and the Donor manpower in a suitable format for a CSV file
     */
    public String toCSVString() {
        return this.user.getDonorID() + "," + this.user.getPhoneNumber() + "," + this.aid + "," + this.quantity + "\n";
    }
    /**
     * Returns the content of aid, quantity, the DonorID, and the Donor manpower
     * 
     * @return returns a string of the content of aid, the value of quantity, the content of DonorID, and the value of manpower in an appropriate string format
     */
    @Override
    public String toString() {
        return this.user.getDonorID() + " " + this.user.getPhoneNumber() + " " + this.aid + " " + this.quantity;
    }
}
