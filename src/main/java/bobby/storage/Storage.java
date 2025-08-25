package bobby.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bobby.exception.BobbyException;

/**
 * Saves the final TaskList. When loaded, returns a List<String>
 * that can then be loaded by the TaskList class
 */
public class Storage {
    private static final String FILE_PATH = "./data/bobby.txt";

    public Storage() {
    }

    /**
     * Loads TaskList from previous runs of Bobby from bobby.txt
     * Empty list if file does not exist
     *
     * @return List<String> of Tasks
     */
    public List<String> load() {
        List<String> tasks = new ArrayList<>();
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            ;
        }
        return tasks;
    }

    /**
     * Saves any command that is successfully executed by Bobby.
     * Creates bobby.txt file if it does not exist, then appends to it.
     *
     * @param tasks
     * @throws BobbyException if some unknown error occurs
     */
    public void save(List<String> tasks) throws BobbyException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (String task : tasks) {
                fw.write(task);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobbyException("An unknown error has occurred. " + e.getMessage());
        }
    }
}