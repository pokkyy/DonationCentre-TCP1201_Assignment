import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This is the DonorDC (Donor Donation Centre) class. The log in menu in this class can be found in the DonorLogin class.
 * The menu provides an interface for the users to navigate the program, allowing them to manipulate data concerning the aids to donate and their own donor account.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public abstract class DonorDC {
    private static Donor currentUser = new Donor();
    /**
     * Constructs an DonorDC object with the contents set to null
     */
    public DonorDC() {}
    /**
     * Returns the amount of donations that has been received by NGOs done by the current Donor logged in
     * 
     * @return the amount of donations the current Donor has donated, and were received by NGOs
     */
    public static int aidsReceivedCount() {
        ArrayList<String> aidsList = DonorAids.getFilteredAidsList(currentUser, "aidsList.csv");

        return aidsList.size();
    }
    /**
     * This is the welcome method that will greet the user once they have successfully logged in to the program.
     * The user's DonorID and Donor's phone number is displayed.
     * 
     * @param user retrieves the current user information from the log in menu
     */
    public static void welcomeUser(Donor user) {
        currentUser = user;
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Welcome, " + currentUser.getDonorID() + "!");
        System.out.println("---------------------------------------------------------------");

        DonorDCMenu();
    }
    /**
     * This method is used in conjunction with the DonorDCMenu. It prints out the list of menu options for the user
     */
    private static void printMenu() {
        System.out.println  ();
        System.out.println  ("--------------------------[DONOR MENU]-------------------------");
        System.out.println  (currentUser.getDonorID() + "\t Phone Number: " + currentUser.getPhoneNumber());
        System.out.println  ("Donations received by NGOs: " + aidsReceivedCount());
        System.out.println  ("---------------------------------------------------------------");
        System.out.println  ("1. Enter aids to donate");
        System.out.println  ("2. View list of aids received by NGOs");
        System.out.println  ("3. View list of aids donated");
        System.out.println  ("4. Change password");
        System.out.println  ("5. Change phone number");
        System.out.println  ("6. Delete account");
        System.out.println  ("0. Log out");
        System.out.print    ("> ");
    }
    /**
     * The DonorDCMenu displays the menu options available for the user and asks them to input their desired option.
     */
    public static void DonorDCMenu() {
        int choice = 5;
        do {
            printMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.");
                System.out.println("Please enter a valid choice.");
            }
            switch (choice) {
                case 1: DonorDCOptions.inputAids(currentUser);       
                        break;
                case 2: viewAidsOptions();  
                        break;
                case 3: viewAidsDonatedOptions();
                        break;
                case 4: DonorDCOptions.changePassword(currentUser);
                        break;
                case 5: DonorDCOptions.changephoneNumber(currentUser);
                        break;
                case 6: boolean logout = DonorDCOptions.deleteAccount(currentUser);
                        if (logout)
                            choice = 0;
            }
        } while (choice != 0);
        System.out.println("Returning to log in menu.");
    }
    /**
     * This method prints a list of options the user will be able to do concerning aids.
     * It is to be used in conjunction with the viewAidsOptions method
     */
    private static void printViewAidsMenu() {
        System.out.println  ();
        System.out.println  ("1. View all donated aids received by NGOs");
        System.out.println  ("2. View list for current Donor");
        System.out.println  ("3. View list for other Donors");
        System.out.println  ("0. Return to main menu");
        System.out.print    ("> ");
    }
    /**
     * This method will show the user a list of options they are able to do concerning aids received by NGOs
     * The user will then be able to input the desired option they wish to do.
     */
    private static void viewAidsOptions() {
        String filename = "aidsList.csv";
        ArrayList<String> aidsList = new ArrayList<>();
        Donor user = new Donor();

        int choice = 5;
        do {
            printViewAidsMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.");
                System.out.println("Please enter a valid choice.");
            }
            switch (choice) {
                case 1: aidsList = DonorAids.getAidsList(filename);
                        DonorDCOptions.printAidsList(aidsList);
                        break;
                case 2: aidsList = DonorAids.getFilteredAidsList(currentUser, filename);
                        DonorDCOptions.printAidsList(aidsList);
                        break;
                case 3: user = DonorDCOptions.DonorIDFilter();
                        aidsList = DonorAids.getFilteredAidsList(user, filename);
                        DonorDCOptions.printAidsList(aidsList);
                        break;
            }
        } while (choice != 0);

        System.out.println("Returning to main menu.");
        DonorDCMenu();
    }
    /**
     * This method prints a list of options the user will be able to do concerning aids.
     * It is to be used in conjunction with the viewAidsOptions method
     */
    private static void printAidDonatedMenu() {
        System.out.println  ();
        System.out.println  ("1. View all aids donated to the DC");
        System.out.println  ("2. View aids donated by current Donor");
        System.out.println  ("3. View aids donated by other Donors");
        System.out.println  ("0. Return to main menu");
        System.out.print    ("> ");
    }
    /**
     * This method will show the user a list of options they are able to do concerning aids.
     * The user will then be able to input the desired option they wish to do.
     */
    private static void viewAidsDonatedOptions() {
        String filename = "aidsDonated.csv";
        ArrayList<String> aidsList = new ArrayList<>();
        Donor user = new Donor();

        int choice = 5;
        do {
            printAidDonatedMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.");
                System.out.println("Please enter a valid choice.");
            }
            switch (choice) {
                case 1: aidsList = DonorAids.getAidsList(filename);
                        DonorDCOptions.printAidsNeeded(aidsList);
                        break;
                case 2: aidsList = DonorAids.getFilteredAidsList(currentUser, filename);
                        DonorDCOptions.printAidsNeeded(aidsList);
                        break;
                case 3: user = DonorDCOptions.DonorIDFilter();
                        aidsList = DonorAids.getFilteredAidsList(user, filename);
                        DonorDCOptions.printAidsNeeded(aidsList);
                        break;
            }
        } while (choice != 0);

        System.out.println("Returning to main menu.");
        DonorDCMenu();
    }
}
