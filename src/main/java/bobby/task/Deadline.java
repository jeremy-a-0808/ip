package bobby.task;

import bobby.exception.BobbyException;

import java.time.LocalDateTime;

/**
 * subclass of Task
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, boolean isMark, String by) throws BobbyException {
        super(description, isMark);
        this.by = parseString(by);
    }

    /**
     * used to categorise tasks
     *
     * @return task type
     */
    @Override
    public int getTaskType() {
        return 1;
    }

    /**
     * converting the task to a String friendly format
     *
     * @return String that is saved in storage
     */
    @Override
    public String toStorage() {
        return super.toStorage() + " /by " + datetimeToStorage(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetimeToString(by) + ")";
    }
}