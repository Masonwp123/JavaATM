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

    default void printError(String message) {
        printSeparator();
        System.out.println(message);
        waitForNextInput();
    }

}