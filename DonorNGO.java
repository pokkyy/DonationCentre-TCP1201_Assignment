/**
 * This is the donor class for donors who have donated aids to the NGO.
 * It is designed to be used in conjunction with the AidsNGO class.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public class DonorNGO {
    private String donorID;
    private String phoneNumber;
    /**
     * Constructs a donorNGO with both donorID and phoneNumber set to null. 
     */
    public DonorNGO(){}
    /**
     * Constructs a donorNGO with the specified donorID and phoneNumber
     * 
     * @param donorID the content of donorID
     * @param phoneNumber the content of phoneNumber
     */
    public DonorNGO(String donorID, String phoneNumber) {
        this.donorID = donorID;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Retuurns a string of donorID and phoneNumber of a donorNGO
     * 
     * @return returns a string consisting of a donorNGO's donorID and phoneNumber
     */
    public String toString() {
        return this.donorID + " " + this.phoneNumber;
    }
}