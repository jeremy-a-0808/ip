import java.util.Scanner;

public class Bobby {
    public static void printList(Task[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + "." + list[i]);
        }
        System.out.println("    ______________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
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
                continue;
            }

            if (input.split(" ").length == 2){
                String[] splits = input.split(" ");
                if (splits[0].equalsIgnoreCase("mark")) {
                    try {
                        int num = Integer.parseInt(splits[1]);
                        if (num <= count) {
                            list[num - 1].mark();
                        }
                        continue;
                    } catch (NumberFormatException e) {
                        continue;
                    }
                } else if (splits[0].equalsIgnoreCase("unmark")) {
                    try {
                        int num = Integer.parseInt(splits[1]);
                        if (num <= count) {
                            list[num - 1].unmark();
                        }
                        continue;
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }

            System.out.println("    added: " + input);
            list[count] = new Task(input);
            count++;

            System.out.println("    ______________________________");
        }
    }
}
