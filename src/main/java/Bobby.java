import java.util.Arrays;
import java.util.Scanner;

public class Bobby {
    public static void printList(String[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + list[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] list = new String[100];
        int count = 0;

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

            if (input.equalsIgnoreCase("list")) {
                printList(list, count);
            }

            else {
                System.out.println("    added: " + input);
                list[count] = input;
                count++;
            }

            System.out.println("    ______________________________");
        }
    }
}
