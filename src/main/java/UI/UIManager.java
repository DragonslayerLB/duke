package UI;

import Tasks.Task;

import java.util.ArrayList;


public interface UIManager {
    /**
     * lists a single task description
     * */
    void listSingleTaskDescription(Task task);

    /**
     * lists descriptions of a collective number of tasks, enumerated
     * */
    void listEnumeratedTaskDescriptions(ArrayList<Task> tasks);

    /**
     * displays any text on the UI
     * */
    void displayText(String text);

    void displayLogo();
}
