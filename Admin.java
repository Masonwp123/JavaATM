import java.util.*;

public class Admin extends AbstractUser {

    public Admin() {
        super("admin", "0000");
    }

    public static void main(String[] args) {
        Admin admin  = new Admin();
        admin.start();
    }

    // begin IHasMenu implementation

    public String menu() {

        printSeparator();

        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Full Customer Report");
        System.out.println("2) Add User");
        System.out.println("3) Set Interest Rate");
        System.out.println("4) Apply Interest to savings accounts");
        System.out.println();
        System.out.print("Action (0-4): ");

        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    public void start() {}

    // end IHasMenu implementation

    // begin AbstractUser implementation

    public String getReport() {
        return "Admin: " + this.getUserName();
    }

    // end AbstractUser implementation
}