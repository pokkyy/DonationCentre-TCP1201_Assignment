import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *  This is the DonorLogin class. It is a specialised login screen and login menu for donors registered to a DC.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public abstract class DonorLogin {
    /**
     * No argument constructor for DonorLogin.
     */
    public DonorLogin () {}
    /**
     * Prints a welcome message to the user.
     * After priting the welcome message, the user is taken to the login menu.
     */
    public static void welcomeScreen() {
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Welcome to the Donor Donation Centre!");
        System.out.println("---------------------------------------------------------------");
        System.out.println();

        loginMenu();
    }
    /**
     * Prints all the login menu options for the user.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("----------------------[Donor login menu]-----------------------");
        System.out.println("1. Login to DC");
        System.out.println("2. Create an account at DC");
        System.out.println("3. Reactive a Donor account");
        System.out.println("0. Exit");
        System.out.print  ("> ");
    }
    /**
     * Perfoms an action based on the menu option the user chooses.
     * If the user chooses 1, they are taken to the login for Donor.
     * If the user chooses 2, they are able to register a new Donor account.
     * If the user chooses 3, they are able to reactivate an inactive Donor account.
     * If the user chooses 0, they are given an exit message before the program finishes.
     */
    public static void loginMenu() {
        int choice = 3;
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
                case 1: loginDonor();
                        break;
                case 2: registerDonor();
                        break;
                case 3: reactiveDonor();
            }
        } while (choice != 0);
        System.out.println("Returning to DC menu.");
    }
    /**
     * This is the login method for the Donor. Users will enter their Donor ID and Donor password. The ID and password are verified before they are taken to the main menu.
     * If either Donor ID or Donor password is incorrect, the method will continue to ask the user for input until the correct one is received.
     */
    private static void loginDonor() {
        Scanner input = new Scanner(System.in);
        Donor user = new Donor();
        String DonorID;

        boolean verification = false;
        do {
            System.out.println();
            System.out.print("Enter Donor ID: ");
            DonorID = input.nextLine();
            System.out.print("Enter password: ");
            String DonorPassword = input.nextLine();

            verification = DonorAccountHandler.verifyAccount(DonorID, DonorPassword);

        } while (!verification);

        user = DonorAccountHandler.getUser(DonorID);
        DonorDC.welcomeUser(user);
    }
    /**
     * Registers a new Donor account into the file.
     * A user will input their desired Donor ID, Donor password, and the phone number.
     * If an account is already registered, the method will continue to ask the user for input until it has received the correct input.
     */
    private static void registerDonor() {
        Scanner input = new Scanner(System.in);

        boolean createAccount = false;

        do {
            System.out.println();
            System.out.print("Enter Donor ID: ");
            String DonorID = input.next();
            input.nextLine();
            System.out.print("Enter password: ");
            String DonorPassword = input.next();
            input.nextLine();
            System.out.print("Input phone number: ");
            String phoneNumber = input.nextLine();
            System.out.println();

            createAccount = DonorAccountHandler.createDonor(DonorID, DonorPassword, phoneNumber);

        } while (!createAccount);
        
        System.out.println("Account created successfully.");
        System.out.println("Please log in again.");
    }
    /**
     * Allows the user to reactive an inactive donor account.
     */
    private static void reactiveDonor() {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the DonorID: ");
        String name = input.next();
        System.out.print("Enter the password: ");
        String password = input.next();

        if (DonorAccountHandler.reactiveDonor(name, password)) {
            System.out.println("Account reactivated successfully.");
            System.out.println("You may log in again");
        } else {
            System.out.println("ERROR: " + name + " could not be reactivated.");
            System.out.println("Please try again.");
        }
    }
}
