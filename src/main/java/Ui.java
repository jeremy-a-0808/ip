import java.util.Scanner;

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
        for (String line: lines) {
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
