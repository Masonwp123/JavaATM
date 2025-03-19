import java.util.*;

class CheckingsAccount implements IHasMenu {

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

    //move to bank later
    public static void printSeparator() {
        System.out.println();
        System.out.println("=====================");
        System.out.println();
    }

    //move to bank later
    public static String waitForNextInput() {
        System.out.println();
        System.out.println("Press ENTER to continue.");
        
        Scanner input = new Scanner(System.in);
        return input.nextLine();
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

    private double getDouble() {
        
        Scanner input = new Scanner(System.in);

        String string = input.nextLine();
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            System.out.print("Invalid Input, please try again: ");
            return getDouble();
        }
    }

    /**
     * CheckingsAccount Modifiers
     */

    void makeDeposit() {

        printSeparator();

        System.out.print("How much do you want to Deposit? (0 to cancel): ");

        Scanner input = new Scanner(System.in);

        double deposit = getDouble();

        if (deposit <= 0.0) {
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

        printSeparator();

        System.out.print("How much do you want to Withdraw? (0 to cancel): ");
        
        Scanner input = new Scanner(System.in);

        double withdrawal = getDouble();

        if (withdrawal <= 0.0) {
            return 0.0;
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