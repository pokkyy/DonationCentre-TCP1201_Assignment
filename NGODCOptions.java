import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is a class that contains all the methods to be used within the NGODC class.
 */
public class NGODCOptions {
    /**
     * No argument constructor for NGODCOptions.
     */
    public NGODCOptions() {}
    /**
     * This changes the password of the current user logged in. The user will first be asked to input their current password, and then the new password of their choice.
     * The purpose of asking the user for their current password is to verify the user beforehand and add another layer of protection to their data.
     * 
     * @param user the user logged in who's password will be changed.
     */
    protected static void changePassword(NGO user) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter old password: ");
        String oldPassword = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        user.changePassword(oldPassword, newPassword);

        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * This changes the manpower of the current user logged in. The user will be asked to input their desired manpower.
     * Should the number be invalid, they will receive an error message.
     * 
     * @param user the user logged in who's manpower will be changed.
     */
    protected static void changeManpower(NGO user) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new manpower: ");
        try {
            int manpower = input.nextInt();
            user.changeManpower(manpower);
            System.out.println();
            System.out.println("Manpower changed successfully.");
            System.out.println("New manpower = " + user.getManpower());
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input");
            System.out.println("Please enter a valid number.");
        }
        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * This deletes the account and data of the current NGO logged in.
     * The NGO's aids needed will also be wiped from the file.
     * 
     * @param user the account to delete.
     * @return true if the account was deleted, false otherwise.
     */
    protected static boolean deleteAccount(NGO user) {
        System.out.println();
        System.out.println("Are you sure you would like to delete your account? This action cannot be undone.");
        System.out.println("[WARNING: Needed aids associated with your account will be deleted.]");
        System.out.println("1. Yes");
        System.out.println("2. No");
        Scanner input = new Scanner(System.in);
        int choice = 5;
        try {
            System.out.print("> ");
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input");
            System.out.println("Please try again.");
        }

        if (choice == 1) {
            user.deleteNGO();
            System.out.println("Account deleted.");
            return true;
        }
        else {
            System.out.println("Returning to main menu.");
            return false;
        }
    }
    /**
     * The user is able to input the aids that they require and the amount they need.
     * The method automatically converts aids entered to a String with proper formatting and capitalisation.
     * If the user inputs an invalid number, they will be prompted to input a correct number before they are able to exit the method.
     * The user's request for aids will then be written in a file of all the aids needed.
     * 
     * @param user the user logged in who will be inputting the aids needed.
     */
    public static void inputAids(NGO user) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print    ("Please enter aids needed: ");
        String aids = input.nextLine();
        aids = aids.substring(0, 1).toUpperCase() + aids.substring(1).toLowerCase();
        System.out.print    ("Please enter the amount needed: ");
        try {
            int quantity = input.nextInt();
            if (quantity < 0)
                do {
                    System.out.print ("ERROR: Invalid input");
                    System.out.println("Please enter a positive number: ");
                    quantity = input.nextInt();
                } while (quantity < 0);
                NGOAids aidsNeeded = new NGOAids(aids, quantity, user);
                aidsNeeded.writeAidstoFile();
        } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
        }
        
        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * The user will be asked to input the desired NGO of their choice. Returns the NGO Account of the user's choosing.
     * If the account does not exist, the user will be asked for more input until the correct input is received.
     * 
     * @return NGO Account of the user's choice.
     */
    protected static NGO NGONameFilter() {
        NGO NGOFilter = new NGO();
        String filter;
        while (true) {
            System.out.println();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the name of the NGO: ");
            filter = input.nextLine();
            NGOFilter = NGOAccountHandler.getUser(filter);
            if (NGOFilter == null)
                System.out.println("User does not exist. Please try again.");
            else
                break;
        }

        return NGOFilter;
    }
    /**
     * This will print the aid's list consisting a donor, an NGO, and the aids donated to the NGO.
     * 
     * @param list the list to be out put by the method.
     */
    public static void printAidsList(ArrayList<String> list) {
        System.out.println();
        if (list.size() != 0) {
            System.out.printf("%-8s %-15s %-20s %-9s %-4s %-9s\n", "Donor", "Phone", "Aids", "Quantity", "NGO",
                    "Manpower");
            for (String aid : list) {
                String[] entry = aid.split(" ");
                System.out.printf("%-8s %-15s %-23s %-6s %-6s %-9s\n", entry[0], entry[1], entry[2], entry[3], entry[4],
                        entry[5]);
            }
            if (list.size() == 1)
                System.out.println(list.size() + " aid was found.");
            else
                System.out.println(list.size() + " aids were found.");
            System.out.println();
        } else
            System.out.println("No aids were found.");
    }
    /**
     * This will print the list of all the aids needed for NGO.
     * 
     * @param list the list to be printed out by the method.
     */
    public static void printAidsNeeded(ArrayList<String> list) {
        System.out.println();
        if (list.size() != 0) {
            System.out.printf("%-20s %-9s %-4s %-9s\n", "Aids", "Quantity", "NGO", "Manpower");
            for (String needed : list) {
                String[] entry = needed.split(" ");
                System.out.printf("%-23s %-6s %-6s %-9s\n", entry[0], entry[1], entry[2], entry[3]);
            }
            if (list.size() == 1)
                System.out.println(list.size() + " aid is needed.");
            else
                System.out.println(list.size() + " aids are needed.");
        } else
            System.out.println("No aids were found.");
    }
}
