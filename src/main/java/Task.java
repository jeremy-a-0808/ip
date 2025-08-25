public abstract class Task {
    protected String description;
    protected boolean isMark;

    public Task(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    public abstract int getTaskType();

    public String getStatusIcon() {
        return (isMark ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isMark = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + this);
        System.out.println("    ______________________________");
    }

    public void unmark() {
        isMark = false;
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + this);
        System.out.println("    ______________________________");
    }

    public String toStorage() {
        int i = isMark ? 1 : 0;
        return (getTaskType() + " | " + i + " | " + description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
