import java.util.*;

public class Customer extends AbstractUser {

    private final CheckingsAccount checkingsAccount = new CheckingsAccount();
    private final SavingsAccount savingsAccount = new SavingsAccount();

    public Customer() {
    }

    public static void main(String[] args) {
        Customer customer  = new Customer();
        customer.start();
    }

    // begin IHasMenu implementation

    public String menu() {

        printSeparator();

        System.out.println("Customer");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Change PIN");
        System.out.println("2) View Checkings Account");
        System.out.println("3) View Savings Account");
        System.out.println();
        System.out.print("Action (0-3): ");

        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            String reponse = menu();
            if (reponse.equals("0")) {
                keepGoing = false;
            } else if (reponse.equals("1")) {
                changePIN();
            } else if (reponse.equals("2")) {
                checkingsAccount.start();
            } else if (reponse.equals("3")) {
                savingsAccount.start();
            } else {
                System.out.println("Invalid Input, please try again.");
            }
        }
    }

    // end IHasMenu implementation

    public void changePIN() {
        printSeparator();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a new PIN: ");

        String PIN = input.nextLine();

        printSeparator();

        //do PIN checks

        System.out.println("PIN is now " + PIN + ".");

        this.setPIN(PIN);

        waitForNextInput();
    }

    // begin AbstractUser implementation

    public String getReport() {
        return "";
    }

    // end AbstractUser implementation
}