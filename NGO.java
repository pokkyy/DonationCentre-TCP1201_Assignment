import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

/**
 * This is the NGO class It contains methods to create an NGO and retrieve data for the NGO's name and manpower
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class NGO extends DCAccount implements Comparable<NGO>{
    private String NGOName;
    private int manpower;

    /**
     * Constructs an NGO with NGOName set to null and manpower set to zero (0).
     */
    public NGO() {}
    /**
     * Constructs an NGO with the specificed NGOName, password, and manpower
     * @param NGOName the content of NGOName
     * @param password the content of password
     * @param manpower the value of manpower
     */
    public NGO(String NGOName, String password, int manpower) {
        super(password);
        this.NGOName = NGOName;
        this.manpower = manpower;
    }
    /**
     * Returns the content of NGOName of an NGO
     * 
     * @return the content of NGOName
     */
    public String getNGOName() {
        return NGOName;
    }
    /**
     * Returns the value of manpower of an NGO
     * 
     * @return the value of manpower
     */
    public int getManpower() {
        return manpower;
    }
    /**
     * Sets the value of manpower to the specificed value desired by the user
     * 
     * @param newManpower the new value of manpower
     */
    public void setManpower(int newManpower) {
        this.manpower = newManpower;
    }
    /**
     * Changes the password of an NGO.
     * The user is required to verify the account with the old password before they are able to change their password
     * The new password is saved into the NGOAccounts.csv file
     * 
     * @param user the current user who wants to change password
     * @param oldPassword the old password of the user who wants to change password
     * @param newPassword the new password for the user
     */
    public void changePassword(NGO user, String oldPassword, String newPassword) {        
        HashSet<NGO> NGOs = NGOAccountHandler.getNGOs();
        boolean verification = NGOAccountHandler.verifyAccount(user.getNGOName(), oldPassword);
        if (verification) {
            try {
                user.setPassword(newPassword);
                Writer NGOFile = new FileWriter("NGOAccounts.csv", false);
                for (NGO i: NGOs) {
                    if (i.compareTo(user) > 0)
                        i = user;
                    NGOFile.write(i.toCSVString());
                }
                NGOFile.close();

                System.out.println("Password changed successfully.");
            } catch (IOException e) {
                System.out.println("Error changing password. Please try again.");
            }
        }
    }
    /**
     * Changes the manpower of an NGO. The new manpower is written into the NGOAccounts.csv file
     * 
     * @param user the current user who wishes to change their manpower
     * @param newManpower the new manpower for the user
     */
    public static void changeManpower(NGO user, int newManpower) {
        HashSet<NGO> NGOs = NGOAccountHandler.getNGOs();
        try {
            user.setManpower(newManpower);
            System.out.println(user);
            Writer NGOFile = new FileWriter("NGOAccounts.csv", false);
            for (NGO i : NGOs) {
                if (i.compareTo(user) > 0)
                    i = user;
                NGOFile.write(i.toCSVString());
            }
            NGOFile.close();
        } catch (IOException e) {
            System.out.println("Error changing manpower. Please try again.");
        }
    }
    /**
     * Compares between two NGO objects based on their NGOName content
     * 
     * @return returns a 1 if they are equal. Returns -1 if they are not equal
     */
    @Override
    public int compareTo(NGO o) {
        if (o.getNGOName().equals(this.getNGOName()))
            return 1;
        else
            return -1;
    }
    /**
     * Returns a string of NGO object in the appropriate CSV format
     * 
     * @return string of NGOName, password, and manpower in CSV format
     */
    public String toCSVString() {
        return this.NGOName + "," + this.getPassword() + "," + this.manpower + "\n";
    }
    /**
     * Returns the content of NGOName, password, and manpower in an appropriate string format
     * 
     * @return returns a string of NGOName, password, and manpower
     */
    @Override
    public String toString() {
        return this.NGOName + " " + this.getPassword() + " " + this.manpower;
    }
    /**
     * Returns a string of an NGO object in a string format
     * 
     * @return string of NGOName and manpower
     */
    public String toStringNGO() {
        return this.NGOName + " " + this.manpower;
    }
}