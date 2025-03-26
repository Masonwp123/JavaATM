import java.util.*;

public class Customer extends AbstractUser {

    private final CheckingsAccount checkingsAccount;
    private final SavingsAccount savingsAccount;

    public Customer(String userName, String PIN) {
        super(userName, PIN);
        checkingsAccount = new CheckingsAccount();
        savingsAccount = new SavingsAccount();
    }

    public Customer() {
        this("Alice", "0000");
    }

    public static void main(String[] args) {
        Customer customer  = new Customer();
        customer.start();
    }

    /**
     * Getters
     */

    public CheckingsAccount getCheckingsAccount() {
        return checkingsAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    // begin IHasMenu implementation

    public String menu() {

        printSeparator();

        System.out.println("Welcome " + getUserName() + "!");
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

        System.out.print("Enter a new PIN (0 to cancel): ");

        String PIN = input.nextLine();

        //cancel PIN change
        if (PIN.equals("0")) {
            return;
        }

        //if PIN is not 4 numbers, tell user and re-prompt
        if (!PIN.matches("^\\d{4}$")) {
            if (printError("PIN must be 4 numberic digits.")) {
                changePIN();
            }
            return;
        }

        printSeparator();

        //do PIN checks

        System.out.println("PIN is now " + PIN + ".");

        this.setPIN(PIN);

        waitForNextInput();
    }

    // begin AbstractUser implementation

    public String getReport() {
        return "User: " + getUserName() + ", Checking: $" + checkingsAccount.getBalanceString() + ", Savings: $" + savingsAccount.getBalanceString();
    }

    // end AbstractUser implementation
}