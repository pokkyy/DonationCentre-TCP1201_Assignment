import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is a class that contains all the methods to be used within the DonorDC
 * class
 */
public class DonorDCOptions {
    /**
     * This changes the password of the current user logged in.
     * The user will first be asked to input their current password, and then the new password of their choice.
     * The purpose of asking the user for their current password is to verify the user beforehand and add another layer of protection to their data.
     * 
     * @param user the user logged in who's password will be changed
     */
    protected static void changePassword(Donor user) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter old password: ");
        String oldPassword = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        user.changePassword(user, oldPassword, newPassword);

        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * This changes the manpower of the current user logged in.
     * The user will be asked to input their desired manpower. Should the number be invalid, they will receive an error message.
     * 
     * @param user the user logged in who's phone number will be changed
     */
    protected static void changephoneNumber(Donor user) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new phone number: ");
        String phoneNumber = input.nextLine();
        if (phoneNumber.length() > 12) {    // msian phone numbers cant be more than 12 characters long
            System.out.println("ERROR: Phone number is too long.");
            do {
                System.out.print("Please enter a valid phone number: ");
                phoneNumber = input.nextLine();
            } while (phoneNumber.length() > 12);
        }
        Donor.changePhoneNumber(user, phoneNumber);
        System.out.println();
        System.out.println("Phone number changed successfully.");
        System.out.println("New phone number: " + user.getPhoneNumber());
        System.out.println();

        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * This deletes the account and data of the current donor logged in.
     * The donor account will be wiped from the file and all of their related aids will also be deleted.
     * 
     * @param user the account to delete.
     * @return true if the account was deleted, false otherwise
     */
    public static boolean deleteAccount(Donor user) {
        System.out.println();
        System.out.println("Are you sure you would like to delete your account? This action cannot be undone.");
        System.out.println("[WARNING: Aids donated will NOT be deleted.]");
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
            DonorAccountHandler.deleteDonor(user);
            System.out.println("Account deleted.");
            return true;
        }
        else {
            System.out.println("Returning to main menu.");
            return false;
        }
    }
    /**
     * The user is able to input the aids that they wish to donate.
     * The method automatically converts aids entered to a String with proper formatting and capitalisation.
     * If the user inputs an invalid number, they will be prompted to input a correct number before they are able to exit the method.
     * The user's request for aids will then be written in a file of all the aids needed.
     * 
     *  @param user the user logged in who will be donating the aids
     */
    protected static void inputAids(Donor user) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print    ("Please enter aids to donate: ");
        String aids = input.nextLine();
        aids = aids.substring(0, 1).toUpperCase() + aids.substring(1).toLowerCase();
        System.out.print    ("Please enter the amount donating: ");
        try {
            int quantity = input.nextInt();
            if (quantity < 0)
                do {
                    System.out.print ("ERROR: Invalid input.");
                    System.out.println("Please enter a positive number: ");
                    quantity = input.nextInt();
                } while (quantity < 0);
                DonorAids aidsDonating = new DonorAids(user, aids, quantity);
                DonorAids.writeAidstoFile(aidsDonating);
        } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
        }
        
        System.out.println();
        System.out.println("Returning to main menu.");
    }
    /**
     * The user will be asked to input the desired Donor of their choice. Returns the Donor Account of the user's choosing.
     * If the account does not exist, the user will be asked for more input until the correct input is received.
     * 
     * @return Donor Account of the user's choice.
     */
    protected static Donor DonorIDFilter() {
        Donor DonorFilter = new Donor();
        String filter;
        while (true) {
            System.out.println();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the ID of the Donor: ");
            filter = input.nextLine();
            DonorFilter = DonorAccountHandler.getUser(filter);
            if (DonorFilter == null)
                System.out.println("User does not exist. Please try again.");
            else
                break;
        }

        return DonorFilter;
    }
    /**
     * This will print the aid's list consisting a donor, the aids donated, and the NGO receiving them
     * 
     * @param list the list to be out put by the method
     */
    protected static void printAidsList(ArrayList<String> list) {
        System.out.println();
        if (list.size() != 0) {
            System.out.printf("%-8s %-15s %-20s %-9s %-4s %-9s\n", "Donor", "Phone", "Aids", "Quantity", "NGO", "Manpower");
            for (String aid : list) {
                String[] entry = aid.split(" ");
                System.out.printf("%-8s %-15s %-23s %-6s %-6s %-9s\n", entry[0], entry[1], entry[2], entry[3], entry[4], entry[5]);
            }
            if (list.size() == 1)
                System.out.println(list.size() + " donation was found.");
            else
                System.out.println(list.size() + " donations were found.");
            System.out.println();
        }
        else
            System.out.println("No donations were found.");
    }
    /**
     * This will print the list of all the aids donated by a Donor
     * 
     * @param list the list to be printed out by the method
     */
    protected static void printAidsNeeded(ArrayList<String> list) {
        System.out.println();
        if (list.size() != 0) {
            System.out.printf("%-8s %-15s %-20s %-9s\n", "Donor", "Phone", "Aids", "Quantity");
            for (String needed: list) {
                String[] entry = needed.split(" ");
                System.out.printf("%-8s %-15s %-23s %-6s\n", entry[0], entry[1], entry[2], entry[3]);
            }
            if (list.size() == 1)
                System.out.println(list.size() + " donation was found.");
            else
                System.out.println(list.size() + " donations were found.");
        }
        else
            System.out.println("No donations were found.");
    }
}
