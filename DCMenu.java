import java.util.Scanner;
/**
 * The starting point of the Donation Centre program.
 * It contains the menu options to choose that are available at the DC.
 * The user is able to choose between 4 options- logging in as a donor, logging in as an NGO, sorting the donations at DC, or exiting the program.
 * 
 * Requires the following files to be able to function properly:
 * DCAccount.java, Donor.java, DonorAids.java, DonorDC.java, DonorLogin.java, aidsList.csv, aidsDonated.csv donorAccounts.csv
 * NGOLogin.java, NGODC.java, NGO.java, DonorNGO.java, NGOAids.java, DonorNGO.java, aidsNeeded.csv
 * 
 * @author Anis Hazirah binti Mohamad Sabry
 * @version 17
 */
public abstract class DCMenu {
    /**
     * This method prints out all the avaliable options the user may choose from.
     */
    private static void printOptions() {
        System.out.println();
        System.out.println("---------------------------[DC MENU]----------------------------");
        System.out.println("1. Log in as Donor");
        System.out.println("2. Log in as NGO");
        System.out.println("3. Sort donations at DC");
        System.out.println("0. Exit program");
        System.out.print  ("> ");
    }
    /**
     * A user will be shown a list of options they can choose from and asked for their input of choice.
     * Based on their input, they will be taken to the option of their choice.
     */
    public static void DCMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        int choice = 5;
        do {
            printOptions();
            try {
                choice = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Please try again!");
            }
            switch (choice) {
                case 1: DonorLogin.welcomeScreen();
                        break;
                case 2: NGOLogin.welcomeScreen();
                        break;
                case 3: // aids one
            }
            if (choice < 0 || choice > 3)
                System.out.println("Incorrect input. Please try again!");
        } while (choice != 0);
    }
    /**
     * This method prints a welcome screen for the user that will pop up once they enter the program
     * The user is required to press enter to enter the program
     */
    private static void printWelcome() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome to the Donation Centre (DC)!");
        System.out.println("----------------------------------------------------------------");
        System.out.println("[PRESS ENTER TO CONTINUE]");
        input.nextLine();
    }
    /**
     * This method prints out the exit message for the user.
     * It will thank them for using the program
     */
    private static void printExitMessage() {
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Thank you for using the Donation Centre program!");
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }
    /**
     * The main method of the program.
     * A user will shown a welcome message and will then be shown a menu full of options they are able to choose from.
     * Once they've chosen to exit the program, they will be greeted with an exit message.
     * 
     * @param args an array of command line arguments for the program
     */
    public static void main(String[] args) {
        printWelcome();
        DCMenuOptions();
        printExitMessage();
    }
}
