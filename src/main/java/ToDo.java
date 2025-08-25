public class ToDo extends Task {
    public ToDo(String description, boolean isMark) {
        super(description, isMark);
    }

    @Override
    public int getTaskType() {
        return 0;
    }

    @Override
    public String toStorage() {
        return super.toStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}