/**
 * Processes commands sent by the user.
 */
public class Parser {
    private TaskList taskList;

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT;

        public boolean expectsArguments() {
            switch (this) {
                case BYE:
                case LIST:
                    return false;
                default:
                    return true;
            }
        }
    }

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if command is valid and processes it accordingly
     *
     * @param input what the user types in
     * @return true if command is valid
     */
    public boolean processCommand(String input) throws BobbyException {
        String[] split = input.split(" ", 2);
        String command = split[0];
        Command commandEnum;
        try {
            commandEnum = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BobbyException("command not recognised.");
        }

        if (commandEnum.expectsArguments() && split.length == 1) {
            throw new BobbyException(command + " requires arguments.");
        }

        if (!commandEnum.expectsArguments() && split.length > 1) {
            throw new BobbyException(command + " should not have any arguments.");
        }

        switch (commandEnum) {
            case BYE:
                Bobby.printGoodbye();
                return false;

            case LIST:
                System.out.println(taskList);
                break;

            case MARK:
            case UNMARK:
            case DELETE:
                int num;

                try {
                    num = Integer.parseInt(split[1]);
                    if (split[0].equalsIgnoreCase("mark")) {
                        taskList.markTask(num);
                    } else if (split[0].equalsIgnoreCase("unmark")) {
                        taskList.unmarkTask(num);
                    } else {
                        taskList.deleteTask(num);
                    }
                } catch (NumberFormatException e) {
                    throw new BobbyException(command + " task number must be an integer.");
                }
                break;

            case TODO:
                taskList.addTask(0, split[1], false);
                break;
            case DEADLINE:
                taskList.addTask(1, split[1], false);
                break;
            case EVENT:
                taskList.addTask(2, split[1], false);
                break;
        }

        return true;
    }
}
