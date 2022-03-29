/**
 * Abstract class to be used with NGO and Donor. This intialises key components needed to manipulate the data and adds an additional 
 * data field for password.
 * The password is used to log into the accounts registered at a DC. The status indicates whether it is an active account or an inactive account.
 * 
 * @author Anis Hazirah binti Mohamad Sabry
 * @version 17
 */
public abstract class DCAccount {
    private String password;
    private boolean status;
    /**
     * Constructs a DCAccount and sets the password and status to null.
     */
    public DCAccount(){}
    /**
     * Constructs a DCAccount and sets the password and status to the specified content.
     * 
     * @param password the password to set.
     * @param status sets the status of the account.
     */
    public DCAccount(String password, boolean status) {
        this.password = password;
        this.status = status;
    }
    /**
     * Returns the password.
     * 
     * @return returns the contents of password.
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Returns the status of the account.
     * 
     * @return true if active, false otherwise.
     */
    public boolean getStatus() {
        return this.status;
    }
    /**
     * Sets the status of the account.
     * 
     * @param status the status of the account. Either true or false.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * Sets the password to the content of newPassword.
     * 
     * @param newPassword the new content to replace password.
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    /**
     * Returns a string in the appropriate CSV format.
     * 
     * @return string in CSV format.
     */
    public abstract String toCSVString();
    /**
     * Returns the content an object in an appropriate string format.
     * 
     * @return returns a string of the object's contents.
     */
    public abstract String toString();
}