import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Saves the commands executed by Bobby as a .txt file
 * When loaded, returns a List<String> that can then be
 * executed as commands by the chatbot to replicate the
 * previous list of tasks
 **/
public class Storage {
    private static final String FILE_PATH = "./data/bobby.txt";

    public Storage() {}

    /**
     * Loads commands from previous runs of Bobby from the bobby.txt file
     *
     * @return List<String> of all previous commands
     * @throws BobbyException if file does not exist, letting the user know that TaskList is empty
     */
    public List<String> load() throws BobbyException {
        List<String> commands = List.of();
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                commands.add(s.nextLine());
            }
            return commands;
        } catch (FileNotFoundException e) {
            throw new BobbyException("No file found. Empty task list initialised");
        }
    }

    /**
     * Saves any command that is successfully executed by Bobby.
     * Creates bobby.txt file if it does not exist, then appends to it.
     *
     * @param command command that user successfully executes
     * @throws BobbyException if some unknown error occurs
     */
    public void save(String command) throws BobbyException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(command);
            fw.close();
        } catch (IOException e){
            throw new BobbyException("An unknown error has occurred. " + e.getMessage());
        }
    }
}