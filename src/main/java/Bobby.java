import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bobby {

    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Bobby() {
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (BobbyException e) {
            System.out.println("    ______________________________");
            System.out.println("    " + e.getMessage());
            System.out.println("    ______________________________");
        }
        parser = new Parser(taskList);
    }

    public static void printGoodbye() {
        System.out.println("    ______________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________");
    }

    public static void printWelcome() {
        System.out.println("    ______________________________");
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        System.out.println("    ______________________________");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean notBye = true;

        printWelcome();

        while (notBye) {
            try {
                input = scanner.nextLine();
                notBye = parser.processCommand(input);
            } catch (BobbyException e) {
                System.out.println("    ______________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    ______________________________");
            }
        }
        try {
            storage.save(taskList.saveTasks());
        } catch (BobbyException e) {
            System.out.println("    ______________________________");
            System.out.println("    " + e.getMessage());
            System.out.println("    ______________________________");
        }
    }

    public static void main(String[] args) {
        new Bobby().run();
    }
}
