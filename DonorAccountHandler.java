import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

/**
 * This is the Donor Account Handler class.
 * It contains methods to handle existing Donor Accounts in a DC
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class DonorAccountHandler {
    /**
     * Returns a true or false if a donor account exists within the file based on their donorID.
     * If a donor account does not exist, a new account is created.
     * 
     * @param donorID the donor id of the user's choosing
     * @param password the password of the donor account the user chooses
     * @param phoneNumber the phone number of the donor
     * @return false if an account exists within the file, true if it does not exist
     */
    public static boolean createDonor(String donorID, String password, String phoneNumber) {
        HashSet<Donor> DonorProfile = getDonors();
        Donor newAccount = new Donor(donorID, password, phoneNumber);

        for (Donor i : DonorProfile)
            if (i.getDonorID().equals(newAccount.getDonorID())) {
                System.out.println(donorID + " already exists. Please try again.");
                return false;
            }
            
        addDonortoFile(newAccount);
        return true;

    }
    /**
     * Verifies if a donor account is valid based on the donorID and the password input by the user.
     * 
     * @param donorID the ID of the donor
     * @param password the password of the donor account
     * @return a true if the account exists and the password is correct, false otherwise.
     */
    public static boolean verifyAccount(String donorID, String password) {
        HashSet<Donor> Donors = getDonors();

        for (Donor i : Donors)
            if (i.getDonorID().equals(donorID))
                if (i.getPassword().equals(password))
                    return true;
                else {
                    System.out.println("Incorrect password. Please try again.");
                    return false;
                }
        System.out.println("This account does not exist. Please try again.");

        return false;
    }
    /**
     * Returns an HashSet containing all the Donor accounts found within the file.
     * The Donor consists of the donorID, the password, and donor's phone number
     * 
     * @return HashSet of all the donor accounts found within the file
     */
    protected static HashSet<Donor> getDonors() {
        HashSet<Donor> Donors = new HashSet<>();
        
        try {
            List<String> list = Files.readAllLines(Paths.get("DonorAccounts.csv")); // reads all the lines from the file and puts in String list
            for (int i = 0; i < list.size(); i++) {
                String[] data = list.get(i).split(","); // store each data into a string array, split by comma
                Donor account = new Donor(data[0], data[2], data[1]);
                Donors.add(account);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load data from file.");
            System.out.println("Please verify that you have the file 'DonorAccounts.csv'");
        }        

        return Donors;
    }
    /**
     * Returns a donor object found within the file based on donorID given
     * 
     * @param donorID the donorID of the donor the user wishes to find
     * @return the donor object associated with the donorID given. If no matches are found, return null.
     */
    public static Donor getUser(String donorID) {
        HashSet<Donor> Donors = getDonors();
        Donor currentUser = new Donor();
        for (Donor i: Donors)
            if (donorID.equals(i.getDonorID())) {
                currentUser = new Donor(i.getDonorID(), i.getPassword(), i.getPhoneNumber());
                return currentUser;
            }
        return null;
    }
    /**
     * Write a donor account into the DonorAccounts.csv file
     * 
     * @param account the account of the donor to be written into the file
     */
    private static void addDonortoFile(Donor account) {
        try {
            Writer DonorFile = new FileWriter("DonorAccounts.csv", true);
            DonorFile.write(account.toCSVString());
            DonorFile.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }
}
