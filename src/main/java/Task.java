import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * abstract class that todo, deadline and event inherit from
 */
public abstract class Task {
    protected String description;
    protected boolean isMark;

    public Task(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    /**
     * used to categorise tasks
     *
     * @return task type
     */
    public abstract int getTaskType();

    /**
     * used for toString()
     *
     * @return isMark status for toString()
     */
    public String getStatusIcon() {
        return (isMark ? "X" : " "); // mark done task with X
    }

    /**
     * marks task
     */
    public void mark() {
        isMark = true;
    }

    /**
     * unmarks task
     */
    public void unmark() {
        isMark = false;
    }

    /**
     * parses user input into dateTime
     *
     * @param dateTime format yyyy-MM-dd HHmm
     * @return LocalDateTime object
     */
    public LocalDateTime parseString(String dateTime) throws BobbyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new BobbyException("Use the yyyy-MM-dd HHmm format.");
        }
    }

    /**
     * returns String to be shown when user sends "list"
     *
     * @param dateTime
     * @return dd/MM/yyyy HHmm format
     */
    public String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }

    /**
     * returns DateTime back to input format to keep in storage
     *
     * @param dateTime
     * @return yyyy-MM-dd HHmm String
     */
    public String dateTimeToStorage(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }

    /**
     * converting the task to a String friendly format
     *
     * @return String that is saved in storage
     */
    public String toStorage() {
        int i = isMark ? 1 : 0;
        return (getTaskType() + " | " + i + " | " + description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
