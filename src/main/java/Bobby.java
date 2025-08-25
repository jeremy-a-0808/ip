/**
 * Chatbot Class
 */
public class Bobby {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Bobby() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (BobbyException e) {
            ui.showMessage(e.getMessage());
        }
        parser = new Parser(taskList);
    }

    public void run() {
        ui.run(parser);
        try {
            storage.save(taskList.saveTasks());
        } catch (BobbyException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Bobby().run();
    }
}
