import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

/**
 * This is the class for donor accounts at the DC.
 * 
 * @author Anis Hazirah binti Mohamad Sabry
 * @version 17
 */
public class Donor extends DCAccount implements Comparable<Donor> {
    private String donorID;
    private String phoneNumber;
    /**
     * Constructs a donor object with the content set to null.
     */
    public Donor(){}
    /**
     * Constructs a donor object with the content set to the specified value.
     * 
     * @param donorID the specified donorID for Donor.
     * @param password the specified password for Donor.
     * @param phoneNumber the specified phone number for Donor.
     * @param status the status of the donor account.
     */
    public Donor(String donorID, String password, String phoneNumber, boolean status) {
        super(password, status);
        this.donorID = donorID;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns the donorID of a donor object.
     * 
     * @return string of donor's donorID.
     */
    public String getDonorID(){
        return this.donorID;
    }
    /**
     * Returns the donor's phone number.
     * 
     * @return string of donor's phone number.
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    /**
     * Sets a new phone number for a donor.
     * 
     * @param newPhoneNumber the new phone number for donor.
     */
    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    /**
     * Changes the Donor's phone number to the specified content.
     * 
     * @param newPhoneNumber the new phone number for Donor.
     */
    protected void changePhoneNumber(String newPhoneNumber) {
        HashSet<Donor> Donors = DonorAccountHandler.getDonors();
        try { 
            this.setPhoneNumber(newPhoneNumber);
            Writer DonorFile = new FileWriter("DonorAccounts.csv", false);
            for (Donor i : Donors) {
                if (i.compareTo(this) > 0)
                    i = this;
                DonorFile.write(i.toCSVString());
            }
            DonorFile.close();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to change phone number.");
            System.out.println("Please verify that you have the file 'DonorAccounts.csv'");
        }
    }
    /**
     * Changes the password of the donor account.
     * The user is required to verify their account before they are able to change their password.
     * The new password is then saved into the file.
     * 
     * @param oldPassword the old password of the user who wants to change their password.
     * @param newPassword the new password for the user.
     */
    protected void changePassword(String oldPassword, String newPassword) {
        HashSet<Donor> Donors = DonorAccountHandler.getDonors();
        boolean verification = DonorAccountHandler.verifyAccount(this.getDonorID(), oldPassword);
        if (verification) {
            try {
                this.setPassword(newPassword);
                Writer DonorFile = new FileWriter("DonorAccounts.csv", false);
                for (Donor i: Donors) {
                    if (i.compareTo(this) > 0)
                        i = this;
                    DonorFile.write(i.toCSVString());
                }
                DonorFile.close();
                System.out.println("Password changed successfully.");
            } catch (IOException e) {
                System.out.println("ERROR: Unable to change password.");
                System.out.println("Please verify that you have the file 'DonorAccounts.csv'");
            }
        }
    }
    /**
     * Write a donor account into the DonorAccounts.csv file.
     */
    protected void addDonortoFile() {
        try {
            Writer DonorFile = new FileWriter("DonorAccounts.csv", true);
            DonorFile.write(this.toCSVString());
            DonorFile.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file. Please try again.");
        }
    }
    /**
     * Sets a donor account's status to inactive.
     * Data concerning the donations they have made are not deleted.
     */
    protected void deleteDonor() {
        HashSet<Donor> accounts = DonorAccountHandler.getDonors();
        try {
            Writer DonorFile = new FileWriter("DonorAccounts.csv", false);
            for (Donor i: accounts) {
                if (i.compareTo(this) > 0)
                    i.setStatus(false);
                DonorFile.write(i.toCSVString());
            }
            DonorFile.close();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to delete account from file");
            System.out.println("Please very that you have the file 'DonorAccounts.csv'");
        }
    }
    /**
     * Compare between two donor accounts based on their DonorID.
     * 
     * @return 1 if they matched, -1 if not.
     */
    @Override
    public int compareTo(Donor o) {
        if (o.getDonorID().equals(this.getDonorID()))
            return 1;
        else
            return -1;
    }
    /**
     * Returns a string of donorID, donor password, and donor phone number in the appropriate CSV format.
     * 
     * @return string of donorID, donor password, and donor phone number in CSV format.
     */
    @Override
    public String toCSVString() {
        return this.donorID + "," + this.phoneNumber + "," + this.getPassword() + "," + this.getStatus() + "\n";
    }
    /**
     * Returns a string of a donor object in string format.
     * 
     * @return string of donorID and donor phone number.
     */
    @Override
    public String toString() {
        return this.donorID + " " + this.phoneNumber;
    }
}