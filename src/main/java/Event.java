import java.time.LocalDateTime;

/**
 * subclass of Task
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, boolean isMark, String from, String to) throws BobbyException {
        super(description, isMark);
        this.from = parseString(from);
        this.to = parseString(to);
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
        return super.toStorage() + " / " + dateTimeToStorage(from) + " / " + dateTimeToStorage(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeToString(from) + " to: " + dateTimeToString(to) + ")";
    }
}
