import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Loads the taskList String from Storage back into a ArrayList<Task>
     *
     * @param tasks String from Storage
     * @throws BobbyException
     */
    public TaskList(List<String> tasks) throws BobbyException {
        this.taskList = new ArrayList<>();
        int taskType = 0;
        boolean isMark = false;

        for (String task: tasks) {
            String[] split = task.split(" | ");
            taskType = Integer.parseInt(split[0]);
            isMark = split[1] == "1" ? true : false;
            this.addTask(taskType, split[2],  isMark);
        }
    }

    /**
     * Adds the task to the taskList. Bobby should first check if the input is more than just the command,
     * if not throw BobbyException when user sends input.
     *
     * @param taskType 0 is ToDo, 1 is Deadline, 2 is Event
     * @param content includes the description, and the dates if necessary. Does not include command.
     * @param isMark whether the task has been marked
     * @throws BobbyException if content does not match required format
     */
    public void addTask(int taskType, String content, boolean isMark) throws BobbyException {
        if (taskType == 0) {
            taskList.add(new ToDo(content, isMark));
        } else {
            String[] split = content.split(" /");
            if (taskType == 1) {
                if (split.length == 2 && split[1].substring(0, 2).equalsIgnoreCase("by")) {
                    taskList.add(new Deadline(split[0], isMark, split[1].substring(3)));
                }
            } else if (taskType == 2) {
                if (split.length == 3 &&
                    split[1].substring(0, 4).equalsIgnoreCase("from") &&
                    split[2].substring(0, 2).equalsIgnoreCase("to")
                ) {
                    taskList.add(new Event(split[0], isMark, split[1].substring(5), split[2].substring(3)));
                }
            } else {
                throw new BobbyException("This is not the right format for adding a task.");
            }
        }
    }

    /**
     * deletes a task from the taskList. Parser checks if taskNum is not an int
     *
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void deleteTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't delete a task that does not exist");
        }
        taskList.remove(taskNum - 1);
    }

    /**
     * marks a task from the taskList. Parser checks if taskNum is not an int
     *
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void markTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't delete a task that does not exist");
        }
        taskList.get(taskNum - 1).mark();
    }

    /**
     * unmarks a task from the taskList. Parser checks if taskNum is not an int
     *
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void unmarkTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't delete a task that does not exist");
        }
        taskList.get(taskNum - 1).unmark();
    }


    /**
     * transforms current taskList into a List<String> to be saved by Storage.
     * Example Format:
     * 0 | 1 | task | description
     * 1 | 0 | task | description | by
     * 2 | 1 | task | description | from | to
     * Same as above, 0 for ToDo, 1 for Deadline, 2 for Event
     *
     * @return List of tasks in String format.
     */
    public List<String> saveTasks() {
        List<String> output = List.of();
        int taskType;
        int isMark;

        for (Task task: taskList) {
            output.add(task.toStorage());
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            str.append(i + ". " + taskList.get(i - 1) + "\n");
        }
        return str.toString();
    }
}
