import java.util.*;
import java.io.*;

public class SavingsAccount extends CheckingsAccount implements Serializable {

    private double interestRate;

    public SavingsAccount() {
        this(0.0);
    }

    public SavingsAccount(double initialBalance) {
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

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}