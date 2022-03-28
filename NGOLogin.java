import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  This is the NGOLogin class.
 *  It is a specialised login screen and login menu for NGO users.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public abstract class NGOLogin {
    /**
     * Constructs an NGOLogin object with the contents set to null
     */
    public NGOLogin() {}
    /**
     * Prints a welcome message to the user.
     * After priting the welcome message, the user is taken to the login menu.
     */
    public static void welcomeScreen() {
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Welcome to the NGO Donation Centre!");
        System.out.println("---------------------------------------------------------------");
        loginMenu();
    }
    /**
     * Prints all the login menu options for the user.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("[NGO login menu]");
        System.out.println("1. Login to DC");
        System.out.println("2. Create an account at DC");
        System.out.println("0. Exit");
        System.out.print("> ");
    }
    /**
     * Perfoms an action based on the menu option the user chooses.
     * If the user chooses 1, they are taken to the login for NGO.
     * If the user chooses 2, they are able to register a new NGO account.
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
                case 1: loginNGO();
                        break;
                case 2: registerNGO();
                        break;
            }
        } while (choice != 0);
        System.out.println("Returning to DC main menu");
    }
    /**
     * This is the login method for the NGO.
     * Users will enter their NGO name and NGO password. The name and password are verified before they are taken to the main menu.
     * If either NGO name or NGO password is incorrect, the method will continue to ask the user for input until the correct one is received.
     */
    public static void loginNGO() {
        Scanner input = new Scanner(System.in);
        NGO user = new NGO();
        String NGOName;

        boolean verification = false;
        do {
            System.out.println();
            System.out.print("Enter NGO name: ");
            NGOName = input.nextLine();
            System.out.print("Enter password: ");
            String NGOPassword = input.nextLine();

            verification = NGOAccountHandler.verifyAccount(NGOName, NGOPassword);

        } while (!verification);

        user = NGOAccountHandler.getUser(NGOName);
        NGODC.welcomeUser(user);
    }
    /**
     * Registers a new NGO account into the file.
     * A user will input their desired NGO name, NGO password, and the manpower.
     * If an account is already registered, the method will continue to ask the user for input until it has received the correct input.
     */
    public static void registerNGO() {
        Scanner input = new Scanner(System.in);

        boolean createAccount = false;

        do {
            System.out.println();
            System.out.print("Enter NGO name: ");
            String NGOName = input.next();
            System.out.print("Enter password: ");
            String NGOPassword = input.next();
            System.out.print("Input manpower: ");
            int manpower = input.nextInt();
            System.out.println();

            createAccount = NGOAccountHandler.createNGO(NGOName, NGOPassword, manpower);

        } while (!createAccount);
        
        System.out.println("Account created successfully.");
        System.out.println("Please log in again.");
    }
}