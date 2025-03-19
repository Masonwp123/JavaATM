import java.util.*;

class SavingsAccount extends CheckingsAccount {

    private double interestRate;

    SavingsAccount() {
        this(0.0);
    }

    SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount(1000.0);
        account.start();
    }

    /**
     * Interest Functions
     */

    public void calcInterest() {
        this.setBalance(this.getBalance() * (this.interestRate + 1));
    }

    public void getInterestRate() {
        printSeparator();
        System.out.println("Current Interest Rate is: " + String.valueOf(this.interestRate));
        waitForNextInput();
    }

    public void setInterestRate() {

        double interestRate = getDouble("What would you like to set the interest rate to? (0 to cancel): ");

        if (interestRate == 0.0) {
            return;
        } else if (interestRate < 0.0) {
            printError("Interest Rate cannot be negative.");
            setInterestRate();
            return;
        }

        this.interestRate = interestRate;
    }

    //test implementation
    public String menu() {
        System.out.println("Checkings Account");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Check Balance");
        System.out.println("2) Make Deposit");
        System.out.println("3) Make Withdrawal");
        System.out.println("4) Get Interest");
        System.out.println("5) Set Interest");
        System.out.println("6) Apply Interest");
        System.out.println();
        System.out.print("Action (0-6): ");

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
            } else if (reponse.equals("4")) {
                getInterestRate();
            } else if (reponse.equals("5")) {
                setInterestRate();
            } else if (reponse.equals("6")) {
                calcInterest();
            } else {
                System.out.println("Invalid Input, please try again.");
            }
        }
    }
}