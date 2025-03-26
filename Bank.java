import java.util.*;
import java.io.*;

public class Bank implements IHasMenu {

    private final Admin admin;
    private CustomerList customers;
    private final String fileName = "data.txt";

    public Bank() {
        this.admin = new Admin();
        this.customers = new CustomerList();

        // Uncomment the following lines to refresh data
        //this.loadSampleCustomers();
        //this.saveCustomers();
        this.loadCustomers();
        this.start();
        this.saveCustomers();
    }

    public static void main(String[] args) {
        new Bank();
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
                this.loginAsAdmin();
            } else if (reponse.equals("2")) {
                this.loginAsCustomer();
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
                this.fullCustomerReport();
            } else if (reponse.equals("2")) {
                this.addUser();
            } else if (reponse.equals("3")) {
                this.setInterestRate();
            } else if (reponse.equals("4")) {
                this.applyInterest();
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
        this.customers.add(new Customer("Alice", "0000"));
        this.customers.add(new Customer("Bob", "0001"));
        this.customers.add(new Customer("Cindy", "0002"));
    }

    //Bank will load sample customers in the case of failure
    public void loadCustomers() {
        try {
            FileInputStream fis = new FileInputStream(fileName); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            this.customers = (CustomerList)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            printSeparator();
            System.out.println(fileName + " Not found, loading sample customers.");            
            this.loadSampleCustomers();
            waitForNextInput();
        } catch (Exception e) {
            printSeparator();
            System.out.print("ERROR: ");
            System.out.println(e.getMessage());
            this.loadSampleCustomers();
            waitForNextInput();
        }
    }

    public void saveCustomers() {
        try {
            FileOutputStream fos = new FileOutputStream(fileName); 
            ObjectOutputStream oos = new ObjectOutputStream(fos); 
            oos.writeObject(this.customers); 
            oos.close(); 
        } catch (Exception e) {
            printSeparator();
            System.out.print("ERROR: ");
            System.out.println(e.getMessage());
            waitForNextInput();
        }
    }

    /**
     * Admin Functions
     * Private because these are sensitive functions
     */

    private void fullCustomerReport() {

        printSeparator();

        for (Customer customer : this.customers) {
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

        //Admin cannot be used
        if (userName.toLowerCase().equals("admin")) {
            if (printError("Admin is a reserved name and cannot be used.")) {
                this.addUser();
            }
            return;
        }

        //Check if the customer is a unique customer
        //Compare lowercase to be certain
        for (Customer customer : this.customers) {
            if (customer.getUserName().toLowerCase().equals(userName.toLowerCase())) {
                if (printError("Customer username is taken.")) {
                    this.addUser();
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
                this.addUser();
            }
            return;
        }

        this.customers.add(new Customer(userName, PIN));

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
                this.setInterestRate();
            }
            return;
        }

        if (interestRate == 0.0) {
            return;
        } else if (interestRate < 0.0) {
            if (printError("Interest Rate cannot be negative.")) {
                this.setInterestRate();
            }
            return;
        }

        for (Customer customer : this.customers) {
            customer.getSavingsAccount().setInterestRate(interestRate);
        }
    }

    private void applyInterest() {

        printSeparator();
        
        for (Customer customer : this.customers) {
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
                this.loginAsAdmin();
            }
            return;
        }

        //start as admin if credentials are correct, otherwise give error message
        if (admin.getUserName().equals(userName) && admin.getPIN().equals(PIN)) {
            this.adminStart();
            return;
        }

        if (printError("Username or Password did not Match.")) {
            this.loginAsAdmin();
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
                this.loginAsCustomer();
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
            this.loginAsCustomer();
        }
    }

    // Simple list of customers that is Serializable
    public static class CustomerList extends ArrayList<Customer> implements Serializable {}

}