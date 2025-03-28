import java.util.*;
import java.io.*;

public class CheckingsAccount implements IHasMenu, Serializable {

    private double balance;

    public CheckingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public CheckingsAccount() {
        this(0.0);
    }

    public static void main(String[] args) {
        CheckingsAccount account = new CheckingsAccount(1000.0);    
        account.start();        
    }

    // begin IHasMenu implementation

    public String menu() {

        printSeparator();
        
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
                this.checkBalance();
                waitForNextInput();
            } else if (reponse.equals("2")) {
                this.makeDeposit();
            } else if (reponse.equals("3")) {
                this.makeWithdrawal();
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
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.println("Account has $" + this.getBalanceString() + ".");        
    }

    protected double getDouble(String message) {

        printSeparator();
        System.out.print(message);
        
        Scanner input = new Scanner(System.in);

        String string = input.nextLine();
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            //retry getting a double
            if (printError("Invalid Input, please try again.")) {
                return this.getDouble(message);
            }
            return 0.0;
        }
    }

    /**
     * CheckingsAccount Modifiers
     */

    void makeDeposit() {

        double deposit = this.getDouble("How much do you want to Deposit? (0 to cancel): ");

        if (deposit == 0.0) {
            return;
        } else if (deposit < 0.0) {
            if (printError("Deposit cannot be negative.")) {
                this.makeDeposit();
            }
            return;
        }

        this.balance += deposit;
        
        printSeparator();
        System.out.println("Deposit of $" + String.valueOf(deposit) + " made.");
        System.out.println();

        this.checkBalance();

        waitForNextInput();

    }

    double makeWithdrawal() {
        
        double withdrawal = this.getDouble("How much do you want to Withdraw? (0 to cancel): ");

        if (withdrawal == 0.0) {
            return 0.0;
        } else if (withdrawal < 0.0) {
            if (printError("Withdrawal cannot be negative.")) {
                return this.makeWithdrawal();
            }
            return 0.0;
        }

        if (withdrawal > this.balance) {
            System.out.println("Not enough money in account, please try again.");
            return 0.0;
        }

        this.balance -= withdrawal;

        printSeparator();
        System.out.println("Withdrawal of $" + String.valueOf(withdrawal) + " made.");
        System.out.println();

        this.checkBalance();

        waitForNextInput();

        return balance;
    }

}