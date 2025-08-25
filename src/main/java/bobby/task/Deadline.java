package bobby.task;

/**
 * subclass of Task
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isMark, String by) {
        super(description, isMark);
        this.by = by;
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
        return super.toStorage() + " / " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}