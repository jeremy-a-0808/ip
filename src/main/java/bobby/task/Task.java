package bobby.task;

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
