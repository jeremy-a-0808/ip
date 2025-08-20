import java.util.Scanner;

public class Bobby {
    public static void addTask(Task[] list, int count, String input) {
        System.out.println("    ______________________________");
        System.out.println("    added: " + input);
        list[count] = new Task(input);
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

    public static boolean isToDo(String input) {
        return input.split(" ")[0].equalsIgnoreCase("todo");
    }

    public static boolean isDeadline(String input) {
        String[] splits = input.split(" /");
        if (splits.length == 2) {
            if (splits[0].length() <= 8 || splits[1].length() <= 2) {
                return false;
            }
            return (splits[0].substring(0, 8).equalsIgnoreCase("deadline")
                    && splits[1].substring(0, 2).equalsIgnoreCase("by"));
        }
        return false;
    }

    public static boolean isEvent(String input) {
        String[] splits = input.split(" /");
        if (splits.length == 3) {
            if (splits[0].length() <= 5 || splits[1].length() <= 4 || splits[2].length() <= 2) {
                return false;
            }
            return (splits[0].substring(0, 5).equalsIgnoreCase("event")
                    && splits[1].substring(0, 4).equalsIgnoreCase("from")
                    && splits[2].substring(0, 2).equalsIgnoreCase("to"));
        }
        return false;
    }

    public static void addToDo(Task[] list, int count, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        list[count] = new ToDo(input.split(" ", 2)[1]);
        System.out.println("      " + list[count]);
        System.out.println("    Now you have " + (count + 1) + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void addDeadline(Task[] list, int count, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        String[] splits = input.split(" /");
        list[count] = new Deadline(splits[0].substring(9), splits[1].substring(3));
        System.out.println("      " + list[count]);
        System.out.println("    Now you have " + (count + 1) + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void addEvent(Task[] list, int count, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        String[] splits = input.split(" /");
        list[count] = new Event(
                splits[0].substring(6),
                splits[1].substring(5),
                splits[2].substring(3)
        );
        System.out.println("      " + list[count]);
        System.out.println("    Now you have " + (count + 1) + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int count = 0;

        printWelcome();

        while (true) {
            input = scanner.nextLine();
            String[] splits = input.split(" ", 2);
            String keyword = splits[0].toLowerCase();

            if (keyword.equals("bye") && splits.length == 1) {
                printGoodbye();
                break;
            }

            switch (keyword) {
                case "list":
                    if (splits.length == 1) {
                        printList(list, count);
                        break;
                    }

                case "mark":
                case "unmark":
                    try {
                        if (isMark(input, count)) {
                            int num = Integer.parseInt(input.split(" ")[1]);
                            list[num - 1].mark();
                        }
                        if (isUnmark(input, count)) {
                            int num = Integer.parseInt(input.split(" ")[1]);
                            list[num - 1].unmark();
                        }
                    } catch (NumberFormatException e) {
                        ;
                    } finally {
                        break;
                    }

                case "todo":
                    if (isToDo(input)) {
                        addToDo(list, count, input);
                        count++;
                    }
                    break;

                case "deadline":
                    if(isDeadline(input)) {
                        addDeadline(list, count, input);
                        count++;
                    }
                    break;

                case "event":
                    if (isEvent(input)) {
                        addEvent(list, count, input);
                        count++;
                    }
                    break;

                default:
            }
        }
    }
}
