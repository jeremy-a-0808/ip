import java.util.Scanner;

public class Bobby {
    public static void addTask(Task[] list, int count, String input) {
        System.out.println("    ______________________________");
        System.out.println("    added: " + input);
        list[count] = new Task(input);
        System.out.println("    ______________________________");
    }

    public static void printGoodbye() {
        System.out.println("    ______________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________");
    }

    public static void printList(Task[] list, int count) {
        System.out.println("    ______________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + "." + list[i]);
        }
        System.out.println("    ______________________________");
    }

    public static void printWelcome() {
        System.out.println("    ______________________________");
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        System.out.println("    ______________________________");
    }

    public static boolean isMark(String input, int count) {
        String[] splits = input.split(" ");
        return (splits.length == 2
                && splits[0].equalsIgnoreCase("mark")
                && Integer.parseInt(splits[1]) <= count);
    }

    public static boolean isUnmark(String input, int count) {
        String[] splits = input.split(" ");
        return (splits.length == 2
                && splits[0].equalsIgnoreCase("unmark")
                && Integer.parseInt(splits[1]) <= count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int count = 0;

        printWelcome();

        while (true) {
            input = scanner.nextLine();

            // Case 1: Exit Chatbot
            if (input.equalsIgnoreCase("bye")) {
                printGoodbye();
                break;
            }

            // Case 2: Print List
            if (input.equalsIgnoreCase("list")) {
                printList(list, count);
                continue;
            }

            // Case 3: Mark/Unmark Task
            try {
                // Case 3a: Mark Task
                if (isMark(input, count)) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list[num - 1].mark();
                }

                // Case 3b: Unmark Task
                if (isUnmark(input, count)) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list[num - 1].unmark();
                }
            } catch (NumberFormatException e) {
                ;
            }

            // Case 4: Add Task
            addTask(list, count, input);
            count++;
        }
    }
}
