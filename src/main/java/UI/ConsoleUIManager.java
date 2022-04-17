package UI;

import Tasks.Task;

import java.util.ArrayList;

public class ConsoleUIManager implements UIManager {
    public void listSingleTaskDescription(Task task) {
        System.out.println(task.getDescription());
    }

    public void listEnumeratedTaskDescriptions(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(task.getIndexedDescription());
        }
    }

    public void displayText(String text) {
        System.out.println(text);
    }
}
