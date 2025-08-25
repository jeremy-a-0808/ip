public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isMark, String from, String to) {
        super(description, isMark);
        this.from = from;
        this.to = to;
    }

    @Override
    public int getTaskType() {
        return 2;
    }

    @Override
    public String toStorage() {
        return super.toStorage() + " / " + from + " / " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
