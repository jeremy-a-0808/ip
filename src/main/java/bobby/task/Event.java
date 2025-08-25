package bobby.task;

/**
 * subclass of Task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isMark, String from, String to) {
        super(description, isMark);
        this.from = from;
        this.to = to;
    }

    /**
     * used to categorise tasks
     *
     * @return task type
     */
    @Override
    public int getTaskType() {
        return 2;
    }

    /**
     * converting the task to a String friendly format
     *
     * @return String that is saved in storage
     */
    @Override
    public String toStorage() {
        return super.toStorage() + " / " + from + " / " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
