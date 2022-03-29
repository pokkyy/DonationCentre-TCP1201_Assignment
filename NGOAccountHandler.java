import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

/**
 * This is the NGO Account Hadling class. It contains methods to handle NGO Accounts in a DC.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class NGOAccountHandler {
    /**
     * No argument constructor for NGOAccountHandler.
     */
    public NGOAccountHandler() {}
    /**
     * Returns a true or false if an NGO exists within the file based on their NGOName.
     * If an NGO does not exist, a new account is created.
     * 
     * @param name the name of an NGO the user chooses.
     * @param password the password of an NGO the user chooses.
     * @param manpower the manpower of an NGO the user chooses.
     * @return false if an account exists within the file. True if it does not exist.
     */
    protected static boolean createNGO(String name, String password, int manpower) {
        HashSet<NGO> registeredUsers = NGOAccountHandler.getNGOs();
        NGO newAccount = new NGO(name, password, manpower, true);

        for (NGO i: registeredUsers)
            if (i.getNGOName().equals(newAccount.getNGOName())) {
                System.out.println(name + " already exists. Please try again.");
                return false;
            }

        newAccount.addNGOtoFile();
        return true;
    }
    /**
     * Verifies if an NGO exists in the file or not and if the password input by the user is correct.
     * 
     * @param name the name of the NGO input by the user.
     * @param password the password of the NGO.
     * @return a true if the account exists and the password is correct, false otherwise.
     */
    protected static boolean verifyAccount(String name, String password) {
        HashSet<NGO> NGOs = getNGOs();
        for (NGO i : NGOs)
            if (i.getNGOName().equals(name))
                if (i.getStatus() == false) {
                    System.out.println("This account is inactive.");
                    System.out.println(" Please log in with a different account");
                    return false;
                }
                else if (i.getPassword().equals(password))
                    return true;
                else {
                    System.out.println("Incorrect password. Please try again.");
                    return false;
                }
        System.out.println("This account does not exist. Please try again.");

        return false;
    }
    /**
     * Returns a HashSet containing all the NGO accounts found within the file.
     * The NGO consists of the NGOName, the password, and manpower.
     * 
     * @return HashSet of all the NGO accounts found.
     */
    protected static HashSet<NGO> getNGOs() {
        HashSet<NGO> NGOs = new HashSet<>();
        
        try {
            List<String> list = Files.readAllLines(Paths.get("NGOAccounts.csv")); // reads all the lines from the file and puts in String list
            for (int i = 0; i < list.size(); i++) {
                String[] data = list.get(i).split(","); // store each data into a string array, split by comma
                int manpower = Integer.valueOf(data[2]);
                NGO account = new NGO(data[0], data[1], manpower, Boolean.valueOf(data[3]));
                NGOs.add(account);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Unable to load data from file.");
            System.out.println("Please verify that you have the file 'NGOAccounts.csv'");
        }        

        return NGOs;
    }
    /**
     * Returns an NGO object found within the file based on the NGOName given.
     * 
     * @param name the name of the NGO the user wants to find.
     * @return the NGO object associated with the name given. If no matches are found, return null.
     */
    protected static NGO getUser(String name) {
        HashSet<NGO> NGOs = getNGOs();
        NGO currentUser = new NGO();
        for (NGO i: NGOs)
            if (name.equals(i.getNGOName())) {
                currentUser = new NGO(i.getNGOName(), i.getPassword(), i.getManpower(), i.getStatus());
                return currentUser;
            }
        return null;
    }
    /**
     * Sets an inactive account's status to active.
     * 
     * @param name the name of the account to be reactivated.
     * @param password the password of the account to be reactivated.
     * @return returns the status of the account. If it is reactivated then it returns true, else false.
     */
    protected static boolean reactiveNGO (String name, String password) {
        HashSet<NGO> accounts = getNGOs();
        boolean success = false;

        for (NGO i: accounts) {
            if (i.getNGOName().equals(name) && i.getStatus() == false)
                if (i.getPassword().equals(password)) {
                    i.setStatus(true);
                    success = true;
                }
        }
        if (success) {
            try {
                Writer NGOFile = new FileWriter("NGOAccounts.csv", false);
                for (NGO i: accounts)
                    NGOFile.write(i.toCSVString());
                NGOFile.close();
            } catch (IOException e) {
                System.out.println("ERROR: Unable to save file");
                System.out.println("Please very that you have the file 'NGOAccounts.csv'");
            }
        }
        return success;
    }
}
