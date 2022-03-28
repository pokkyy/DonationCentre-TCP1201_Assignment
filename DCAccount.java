/**
 * Abstract class to be used with NGO and Donor. This intialises key components needed to manipulate the data and adds an additional 
 * data field for password.
 * The password is used to log into the accounts registered at a DC
 * 
 * @author Anis Hazirah binti Mohamad Sabry
 * @version 17
 */
public abstract class DCAccount {
    private String password;
    /**
     * Constructs a DCAccount and sets the password to null
     */
    public DCAccount(){}
    /**
     * Constructs a DCAccount and sets the password to the specified content
     * 
     * @param password the password to set
     */
    public DCAccount(String password) {
        this.password = password;
    }
    /**
     * Returns the password
     * 
     * @return returns the content password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Sets the password to the content of newPassword
     * 
     * @param newPassword the new content to replace password
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    /**
     * Returns a string in the appropriate CSV format
     * 
     * @return string in CSV format
     */
    public abstract String toCSVString();
    /**
     * Returns the content an object in an appropriate string format
     * 
     * @return returns a string of the object's contents
     */
    public abstract String toString();
}