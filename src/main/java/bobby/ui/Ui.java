package bobby.ui;

import java.util.Scanner;

import bobby.exception.BobbyException;
import bobby.parser.Parser;

/**
 * Ui class that interacts with the user by sending messages
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * takes a string and converts it into indented and separated sout
     *
     * @param message to be converted
     */
    public static void showMessage(String message) {
        System.out.println("    ________________________________________________________________");
        String[] lines = message.split("\\R");
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    ________________________________________________________________");
    }

    /**
     * prints the welcome message
     */
    public static void showWelcome() {
        showMessage("Hello I'm Bobby!\nWhat can I do for you?");
    }

    /**
     * prints the exit message
     */
    public static void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Starts the messaging loop until user terminates with "bye"
     * @param parser
     */
    /**
     *
     * @return welcome String
     */
    public static String outputWelcome() {
        return "Hello I'm Bobby!\nWhat can I do for you?";
    }

    /**
     *
     * @return goodbye String
     */
    public static String outputGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public void run(Parser parser) {
        String input;
        boolean notBye = true;

        showWelcome();
        while (notBye) {
            try {
                input = scanner.nextLine();
                notBye = parser.processCommand(input);
            } catch (BobbyException e) {
                this.showMessage(e.getMessage());
            }
        }
    }
}
