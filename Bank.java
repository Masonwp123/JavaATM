import java.util.*;
import java.io.*;

public class Bank implements IHasMenu {

    private final Admin admin;
    private final ArrayList<Customer> customers;

    public Bank() {
        admin = new Admin();
        customers = new ArrayList<Customer>();
        loadSampleCustomers();
        //init stuff
    }

    public static void main(String[] args) {
        Bank bank  = new Bank();
        bank.start();
    }

    // begin IHasMenu implementation

    public String menu() {

        printSeparator();

        System.out.println("ATM Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Login as Admin");
        System.out.println("2) Login as Customer");
        System.out.println();
        System.out.print("Action (0-2): ");

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
                loginAsAdmin();
            } else if (reponse.equals("2")) {
                loginAsCustomer();
            } else {
                System.out.println("Invalid Input, please try again.");
            }
        }
    }

    public void adminStart() {
        boolean keepGoing = true;
        while (keepGoing) {
            String reponse = admin.menu();
            if (reponse.equals("0")) {
                keepGoing = false;
            } else if (reponse.equals("1")) {
                fullCustomerReport();
            } else if (reponse.equals("2")) {
                addUser();
            } else if (reponse.equals("3")) {
                setInterestRate();
            } else if (reponse.equals("4")) {
                applyInterest();
            } else {
                System.out.println("Invalid Input, please try again.");
            }
        }
    }

    // end IHasMenu implementation

    /**
     * Serialization Functions
     */

    public void loadSampleCustomers() {
        customers.add(new Customer("Alice", "0000"));
        customers.add(new Customer("Bob", "0001"));
        customers.add(new Customer("Cindy", "0002"));
    }

    public void loadCustomers() {
        
    }

    public void saveCustomers() {
        File customerFile = new File("data.txt");
    }

    /**
     * Admin Functions
     * Private because these are sensitive functions
     */

    private void fullCustomerReport() {

        printSeparator();

        for (Customer customer : customers) {
            System.out.println(customer.getReport());
        }

        waitForNextInput();

    }

    private void addUser() {

        Scanner input = new Scanner(System.in);

        printSeparator();

        System.out.println("Add User:");
        System.out.println();
        System.out.print("Enter User Name: ");

        String userName = input.nextLine();

        //Check if the customer is a unique customer
        //Compare lowercase to be certain
        for (Customer customer : customers) {
            if (customer.getUserName().toLowerCase().equals(userName.toLowerCase())) {
                if (printError("Customer username is taken.")) {
                    addUser();
                }
                return;
            }
        }

        System.out.println();
        System.out.print("Enter PIN: ");

        String PIN = input.nextLine();

        //if PIN is not 4 numbers, tell user and re-prompt
        if (!PIN.matches("^\\d{4}$")) {
            if (printError("PIN must be 4 numberic digits.")) {
                addUser();
            }
            return;
        }

        //TODO: user uniqueness

        customers.add(new Customer(userName, PIN));

        printSeparator();

        System.out.println("User Successfully Added.");

        waitForNextInput();

    }

    private void setInterestRate() {

        printSeparator();
        System.out.print("Enter a new interest rate: ");
        
        Scanner input = new Scanner(System.in);

        String string = input.nextLine();
        double interestRate = 0.0;
        try {
            interestRate =  Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            if (printError("Invalid Input, please try again.")) {
                setInterestRate();
            }
            return;
        }

        if (interestRate == 0.0) {
            return;
        } else if (interestRate < 0.0) {
            if (printError("Interest Rate cannot be negative.")) {
                setInterestRate();
            }
            return;
        }

        for (Customer customer : customers) {
            customer.getSavingsAccount().setInterestRate(interestRate);
        }
    }

    private void applyInterest() {

        printSeparator();
        
        for (Customer customer : customers) {
            customer.getSavingsAccount().calcInterest();
        }

        System.out.println();
        System.out.println("Interest applied Successfully.");
        
        waitForNextInput();
    }

    /**
     * Login Functions
     */

    private void loginAsAdmin() {

        Scanner input = new Scanner(System.in);

        printSeparator();

        System.out.println("Login as Admin:");
        System.out.println();
        System.out.print("Enter User Name: ");

        String userName = input.nextLine();

        System.out.println();
        System.out.print("Enter PIN: ");

        String PIN = input.nextLine();

        //if PIN is not 4 numbers, tell user and re-prompt
        if (!PIN.matches("^\\d{4}$")) {
            if (printError("PIN must be 4 numberic digits.")) {
            loginAsAdmin();
            }
            return;
        }

        //start as admin if credentials are correct, otherwise give error message
        if (admin.getUserName().equals(userName) && admin.getPIN().equals(PIN)) {
            adminStart();
            return;
        }

        if (printError("Username or Password did not Match.")) {
            loginAsAdmin();
        }
    }

    private void loginAsCustomer() {
        
        Scanner input = new Scanner(System.in);

        printSeparator();

        System.out.println("Login as Customer:");
        System.out.println();
        System.out.print("Enter User Name: ");

        String userName = input.nextLine();

        System.out.println();
        System.out.print("Enter PIN: ");

        String PIN = input.nextLine();

        //if PIN is not 4 numbers, tell user and re-prompt
        if (!PIN.matches("^\\d{4}$")) {
            if (printError("PIN must be 4 numberic digits.")) {
                loginAsCustomer();
            }
            return;
        }

        for (Customer customer : customers) {
            //start as customer if credentials are correct, otherwise give error message
            if (customer.getUserName().equals(userName) && customer.getPIN().equals(PIN)) {
                customer.start();
                return;
            }
        }

        if (printError("Username or Password did not Match.")) {
            loginAsCustomer();
        }
    }

}