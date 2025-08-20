import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    public static void addTask(ArrayList<Task> list, String input) {
        System.out.println("    ______________________________");
        System.out.println("    added: " + input);
        list.add(new Task(input));
        System.out.println("    ______________________________");
    }

    public static boolean isMark(ArrayList<Task> list, String input) {
        String[] splits = input.split(" ");
        return (splits.length == 2
                && splits[0].equalsIgnoreCase("mark")
                && Integer.parseInt(splits[1]) <= list.size());
    }

    public static boolean isUnmark(ArrayList<Task> list, String input) {
        String[] splits = input.split(" ");
        return (splits.length == 2
                && splits[0].equalsIgnoreCase("unmark")
                && Integer.parseInt(splits[1]) <= list.size());
    }

    public static void printGoodbye() {
        System.out.println("    ______________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ______________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + "." + list.get(i));
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
        String[] splits = input.split(" ", 2);
        return (splits.length == 2 && splits[0].equalsIgnoreCase("todo"));
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

    public static void addToDo(ArrayList<Task> list, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        list.add(new ToDo(input.split(" ", 2)[1]));
        System.out.println("      " + list.get(list.size() - 1));
        System.out.println("    Now you have " + list.size() + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void addDeadline(ArrayList<Task> list, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        String[] splits = input.split(" /");
        list.add(new Deadline(splits[0].substring(9), splits[1].substring(3)));
        System.out.println("      " + list.get(list.size() - 1));
        System.out.println("    Now you have " + list.size() + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void addEvent(ArrayList<Task> list, String input) {
        System.out.println("    ______________________________");
        System.out.println("    Got it. I've added this task:");
        String[] splits = input.split(" /");
        list.add(new Event(
                splits[0].substring(6),
                splits[1].substring(5),
                splits[2].substring(3)
        ));
        System.out.println("      " + list.get(list.size() - 1));
        System.out.println("    Now you have " + list.size() + " tasks in the list");
        System.out.println("    ______________________________");
    }

    public static void main(String[] args) throws BobbyException {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> list = new ArrayList<>();

        printWelcome();

        while (true) {
            try {
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
                            printList(list);
                            break;
                        } else {
                            throw new BobbyException("OOPS! list keyword should not have anything behind it.");
                        }

                    case "mark":
                    case "unmark":
                        try {
                            if (isMark(list, input)) {
                                int num = Integer.parseInt(input.split(" ")[1]);
                                if (num > list.size()) {
                                    throw new BobbyException("OOPS! You cant mark a task that does not exist.");
                                }
                                list.get(num - 1).mark();
                            }
                            if (isUnmark(list, input)) {
                                int num = Integer.parseInt(input.split(" ")[1]);
                                if (num > list.size()) {
                                    throw new BobbyException("OOPS! You cant unmark a task that does not exist.");
                                }
                                list.get(num - 1).unmark();
                            }
                        } catch (NumberFormatException e) {
                            throw new BobbyException("OOPS! Task to mark/unmark must be an integer!");
                        } finally {
                            break;
                        }

                    case "todo":
                        if (isToDo(input)) {
                            addToDo(list, input);
                        } else {
                            throw new BobbyException("OOPS! Description of todo cannot be empty.");
                        }
                        break;

                    case "deadline":
                        if (isDeadline(input)) {
                            addDeadline(list, input);
                        } else {
                            throw new BobbyException("OOPS! Setting a deadline should follow this format\n" +
                                    "    'deadline {description} /by {by}'.");
                        }
                        break;

                    case "event":
                        if (isEvent(input)) {
                            addEvent(list, input);
                        } else {
                            throw new BobbyException("OOPS! Setting a deadline should follow this format\n" +
                                    "    'event {description} /from {from} /to {to}'.");
                        }
                        break;

                    default:
                        throw new BobbyException("OOPS! I have no idea what this means");
                }
            } catch (BobbyException e) {
                System.out.println("    ______________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    ______________________________");
            }
        }
    }
}
