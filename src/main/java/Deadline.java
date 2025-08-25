public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isMark, String by) {
        super(description, isMark);
        this.by = by;
    }

    @Override
    public int getTaskType() {
        return 1;
    }

    @Override
    public String toStorage() {
        return super.toStorage() + " / " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}