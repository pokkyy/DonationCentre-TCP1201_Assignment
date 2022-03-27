import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

/**
 * This is the class for donor.
 * 
 * @author Anis Hazirah binti Mohamad Sabry
 * @version 17
 */
public class Donor extends DCAccount implements Comparable<Donor> {
    private String donorID;
    private String phoneNumber;

    /**
     * Constructs a donor object with the content set to null
     */
    public Donor(){}
    /**
     * Constructs a donor object with the content set to the specified value
     * 
     * @param donorID the specified donorID for Donor
     * @param password the specified password for Donor
     * @param phoneNumber the specified phone number for Donor
     */
    public Donor(String donorID, String password, String phoneNumber) {
        super(password);
        this.donorID = donorID;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns the donorID of a donor object
     * 
     * @return string of donor's donorID
     */
    public String getDonorID(){
        return this.donorID;
    }
    /**
     * Returns the donor's phone number
     * 
     * @return string of donor's phone number
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    /**
     * Sets a new phone number for a donor
     * 
     * @param newPhoneNumber the new phone number for donor
     */
    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    /**
     * Changes the Donor's phone number to the specified content
     * 
     * @param user the user who wishes to change their phone number
     * @param newPhoneNumber the new phone number for Donor
     */
    public static void changePhoneNumber(Donor user, String newPhoneNumber) {
        HashSet<Donor> Donors = DonorAccountHandler.getDonors();
        try { 
            user.setPhoneNumber(newPhoneNumber);
            System.out.println(user);
            Writer DonorFile = new FileWriter("DonorAccounts.csv", false);
            for (Donor i : Donors) {
                if (i.compareTo(user) > 0)
                    i = user;
                DonorFile.write(i.toCSVString());
            }
            DonorFile.close();
        } catch (IOException e) {
            System.out.println("Error changing manpower. Please try again.");
        }
    }
    /**
     * Changes the password of the donor account.
     * The user is required to verify their account before they are able to change their password.
     * The new password is then saved into the file.
     * 
     * @param user the user who wants to change their password
     * @param oldPassword the old password of the user who wants to change their password
     * @param newPassword the new password for the user
     */
    public void changePassword(Donor user, String oldPassword, String newPassword) {
        HashSet<Donor> Donors = DonorAccountHandler.getDonors();
        boolean verification = DonorAccountHandler.verifyAccount(user.getDonorID(), oldPassword);
        if (verification) {
            try {
                user.setPassword(newPassword);
                Writer DonorFile = new FileWriter("DonorAccounts.csv", false);
                for (Donor i: Donors) {
                    if (i.compareTo(user) > 0)
                        i = user;
                    DonorFile.write(i.toCSVString());
                }
                DonorFile.close();

                System.out.println("Password changed successfully.");
            } catch (IOException e) {
                System.out.println("Error changing password. Please try again.");
            }
        }
    }
    public int compareTo(Donor o) {
        if (o.getDonorID().equals(this.getDonorID()))
            return 1;
        else
            return -1;
    }
    /**
     * Returns a string of donorID, donor password, and donor phone number in the appropriate CSV format
     * 
     * @return string of donorID, donor password, and donor phone number in CSV format
     */
    @Override
    public String toCSVString() {
        return this.donorID + "," + this.phoneNumber + "," + this.getPassword() + "\n";
    }
    /**
     * Returns a string of a donor object in string format
     * 
     * @return string of donorID and donor phone number
     */
    @Override
    public String toString() {
        return this.donorID + " " + this.phoneNumber;
    }
}