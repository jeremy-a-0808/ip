import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("    ______________________________");
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        System.out.println("    ______________________________");

        while (true) {
            input = scanner.nextLine();
            System.out.println("    ______________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ______________________________");
                break;
            }

            System.out.println("    " + input);
            System.out.println("    ______________________________");
        }
    }
}
