import java.util.*;

interface IHasMenu {

    String menu();

    void start();

    /**
     * default methods useful inside of menus
     */

    default void printSeparator() {
        System.out.println();
        System.out.println("=====================");
        System.out.println();
    }

    default String waitForNextInput() {
        System.out.println();
        System.out.println("Press ENTER to continue.");
        
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    default boolean askToTryAgain() {
        printSeparator();
        System.out.print("Would you like to try again (y/n)? ");
        
        Scanner input = new Scanner(System.in);
        return input.nextLine().equals("y");
    }

    default boolean printError(String message) {
        printSeparator();
        System.out.print("ERROR: ");
        System.out.println(message);
        return askToTryAgain();
    }

}