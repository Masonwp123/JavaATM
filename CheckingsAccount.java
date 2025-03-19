import java.util.*;
import java.util.function.Supplier;

public class CheckingsAccount implements IHasMenu {

    private double balance;

    CheckingsAccount() {
        this(0.0);
    }

    CheckingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public static void main(String[] args) {
        CheckingsAccount account = new CheckingsAccount(1000.0);    
        account.start();        
    }

    // begin IHasMenu implementation

    public String menu() {
        System.out.println("Checkings Account");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Check Balance");
        System.out.println("2) Make Deposit");
        System.out.println("3) Make Withdrawal");
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
                printSeparator();
                checkBalance();
                waitForNextInput();
            } else if (reponse.equals("2")) {
                makeDeposit();
            } else if (reponse.equals("3")) {
                makeWithdrawal();
            } else {
                System.out.println("Invalid Input, please try again.");
            }
        }
    }

    // end IHasMenu implementation

    /**
     * Balance Functions
     */

    public double getBalance() {
        return this.balance;
    }

    public String getBalanceString() {
        return String.valueOf(this.balance);
    }

    public void setBalance(double balance) {
        if (balance < 0.0) {
            System.out.println("Balance cannot be negative.");
            return;
        }
        this.balance = balance;
        printSeparator();
        checkBalance();
        waitForNextInput();
    }

    public void checkBalance() {
        System.out.println("Account has $" + getBalanceString() + ".");        
    }

    protected double getDouble(String message) {

        printSeparator();
        System.out.print(message);
        
        Scanner input = new Scanner(System.in);

        String string = input.nextLine();
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            printError("Invalid Input, please try again.");
            //retry getting a double
            return getDouble(message);
        }
    }

    /**
     * CheckingsAccount Modifiers
     */

    void makeDeposit() {

        double deposit = getDouble("How much do you want to Deposit? (0 to cancel): ");

        if (deposit == 0.0) {
            return;
        } else if (deposit < 0.0) {
            printError("Deposit cannot be negative.");
            makeDeposit();
            return;
        }

        this.balance += deposit;
        
        printSeparator();
        System.out.println("Deposit of $" + String.valueOf(deposit) + " made.");
        System.out.println();

        checkBalance();

        waitForNextInput();

    }

    double makeWithdrawal() {
        
        double withdrawal = getDouble("How much do you want to Withdraw? (0 to cancel): ");

        if (withdrawal == 0.0) {
            return 0.0;
        } else if (withdrawal < 0.0) {
            printError("Withdrawal cannot be negative.");
            return makeWithdrawal();
        }

        if (withdrawal > balance) {
            System.out.println("Not enough money in account, please try again.");
            return 0.0;
        }

        this.balance -= withdrawal;

        printSeparator();
        System.out.println("Withdrawal of $" + String.valueOf(withdrawal) + " made.");
        System.out.println();

        checkBalance();

        waitForNextInput();

        return balance;
    }

}