import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the NGODC (NGO Donation Centre) class. The log in menu in this class can be found in the NGOLogin class.
 * The menu provides an interface for the users to navigate the program, allowing them to manipulate data concerning the aids and their own NGO account.
 * 
 * @author Anis Hazirah Mohamad Sabry
 * @version 17
 */
public abstract class NGODC {
    private static NGO currentUser = new NGO();
    /**
     * This is the welcome method that will greet the user once they have successfully logged in to the program.
     * The user's NGOName and NGO's manpower is displayed.
     * 
     * @param user retrieves the current user information from the log in menu
     */
    public static void welcomeUser(NGO user) {
        currentUser = user;
        int aidsReceivedCount = NGOAids.aidsReceivedCount(currentUser);
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Welcome, " + currentUser.getNGOName() + "!");
        System.out.println("---------------------------------------------------------------");
        NGODCMenu();
    }
    /**
     * This method is used in conjunction with the NGODCMenu. It prints out the list of menu options for the user
     */
    private static void printMenu() {
        int aidsReceivedCount = NGOAids.aidsReceivedCount(currentUser);
        System.out.println  ();
        System.out.println  ("---------------------------------------------------------------");
        System.out.println  (currentUser.getNGOName() + "\t manpower: " + currentUser.getManpower());
        System.out.println  ("Aids received: " + aidsReceivedCount);
        System.out.println  ("---------------------------------------------------------------");
        System.out.println  ("1. Enter aids needed");
        System.out.println  ("2. View list of aids received");
        System.out.println  ("3. View list of aids needed");
        System.out.println  ("4. Change password");
        System.out.println  ("5. Change manpower");
        System.out.println  ("0. Log out");
        System.out.print    ("> ");
    }
    /**
     * The NGODCMenu displays the menu options available for the user and asks them to input their desired option.
     */
    public static void NGODCMenu() {
        int choice = 5;
        do {
            printMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid choice.");
            }
            switch (choice) {
                case 1: NGODCOptions.inputAids(currentUser);       
                        break;
                case 2: viewAidsOptions();  
                        break;
                case 3: viewAidsNeededOptions();
                        break;
                case 4: NGODCOptions.changePassword(currentUser);
                        break;
                case 5: NGODCOptions.changeManpower(currentUser);
                        break;
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
        System.out.println  ("1. View list for current NGO");
        System.out.println  ("2. View list for other NGO");
        System.out.println  ("3. View all aids received for every NGO");
        System.out.println  ("0. Return to main menu");
        System.out.print    ("> ");
    }
    /**
     * This method will show the user a list of options they are able to do concerning aids.
     * The user will then be able to input the desired option they wish to do.
     */
    private static void viewAidsOptions() {
        String filename = "aidsList.csv";
        ArrayList<String> aidsList = new ArrayList<>();
        NGO user = new NGO();

        int choice = 5;
        do {
            printViewAidsMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid choice.");
            }
            switch (choice) {
                case 1: aidsList = NGOAids.getFilteredAidsList(currentUser, filename);
                        NGODCOptions.printAidsList(aidsList);
                        break;
                case 2: user = NGODCOptions.NGONameFilter();
                        aidsList = NGOAids.getFilteredAidsList(user, filename);
                        NGODCOptions.printAidsList(aidsList);
                        break;
                case 3: aidsList = NGOAids.getAidsList(filename);
                        NGODCOptions.printAidsList(aidsList);
                        break;
            }
        } while (choice != 0);

        System.out.println("Returning to main menu.");
        NGODCMenu();
    }
    /**
     * This method prints a list of options the user will be able to do concerning aids.
     * It is to be used in conjunction with the viewAidsOptions method
     */
    private static void printAidsNeededMenu() {
        System.out.println  ();
        System.out.println  ("1. View aids needed for current NGO");
        System.out.println  ("2. View aids needed for other NGOs");
        System.out.println  ("3. View all aids needed for every NGO");
        System.out.println  ("0. Return to main menu");
        System.out.print    ("> ");
    }
    /**
     * This method will show the user a list of options they are able to do concerning aids.
     * The user will then be able to input the desired option they wish to do.
     */
    private static void viewAidsNeededOptions() {
        String filename = "aidsNeeded.csv";
        ArrayList<String> aidsList = new ArrayList<>();
        NGO user = new NGO();

        int choice = 5;
        do {
            printAidsNeededMenu();
            try {
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid choice.");
            }
            switch (choice) {
                case 1: aidsList = NGOAids.getFilteredAidsList(currentUser, filename);
                        NGODCOptions.printAidsNeeded(aidsList);
                        break;
                case 2: user = NGODCOptions.NGONameFilter();
                        aidsList = NGOAids.getFilteredAidsList(user, filename);
                        NGODCOptions.printAidsNeeded(aidsList);
                        break;
                case 3: aidsList = NGOAids.getAidsList(filename);
                        NGODCOptions.printAidsNeeded(aidsList);
                        break;
            }
        } while (choice != 0);

        System.out.println("Returning to main menu.");
        NGODCMenu();
    }
}