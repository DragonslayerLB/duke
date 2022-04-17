import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * General utility class
 * */
public class DukeProgrammeUtility {
    /*
     * Reads the next line of user input
     * */
    public static String getNextLineOfInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        return line;
    }

    public static Boolean isActionCommand(String commandStr) {
        String[] subStr = commandStr.split(" ");
        try {
            if (subStr.length == 2) {
                int index = Integer.parseInt(subStr[1]);
                return subStr[0].equals(Commands.MARK) || subStr[0].equals(Commands.UNMARK) || subStr[0].equals(Commands.DELETE);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ActionCommand processActionCommand(String commandStr) throws DukeException {
        String[] subStr = commandStr.split(" ");
        TaskType type;

        if (subStr[0].equals(Commands.MARK)) {
            type = TaskType.MARK;
        } else if (subStr[0].equals(Commands.UNMARK)) {
            type = TaskType.UNMARK;
        } else if (subStr[0].equals(Commands.DELETE)) {
            type = TaskType.DELETE;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return new ActionCommand(type, Integer.parseInt(subStr[1]));
    }

    public static String getFormattedTaskDescription(Task task) {
        return "[" + (task.isDone ? "X" : " ") + "] " + task.description;
    }

    public static void printToConsoleSingleTask(Task task) {
        System.out.println(getFormattedTaskDescription(task));
    }

    public static void printToConsoleEnumerated(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(task.getIndexedDescription());
        }
    }

    public static Task getTask(int index, String commandStr) throws DukeException {
        String[] words = commandStr.split(" ");

        String description = "";
        String time = "";

        if (words[0].equals(Commands.TODO)) {
            if (words.length == 1) {
                throw new DukeException("The description of a " + words[0] + " cannot be empty.");
            }

            for (int i = 1; i < words.length; i++) {
                description += words[i];

                if (i != words.length - 1) {
                    description += " ";
                }
            }

            return new TODOTask(index, description);
        } else if (words[0].equals(Commands.EVENT) || words[0].equals(Commands.DEADLINE)) {
            int atIndex = -1;
            if (words.length == 1) {
                throw new DukeException("The description of a " + words[0] + " cannot be empty.");
            }

            for (int i = 1; i < words.length; i++) {
                if (words[i].equals(words[0].equals(Commands.EVENT) ? "/at" : "/by")) {
                    atIndex = i;
                    break;
                }
                description += words[i];
                description += " ";
            }

            if (atIndex + 1 >= words.length || atIndex == -1) {
                throw new DukeException("failed");
            }

            for (int i = atIndex + 1; i < words.length; i++) {
                time += words[i];

                if (i != words.length - 1) {
                    time += " ";
                }
            }

            return new EventTask(index, description, time);
        }

        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    static void saveTaskList(ArrayList<Task> tasks) throws DukeException {
        String contentToSave = "";
        for (int i = 0; i < tasks.size(); i++) {
            contentToSave += tasks.get(i).getDbEntryDescription();

            if (i < tasks.size() - 1) {
                contentToSave += "\n";
            }
        }

        try {
            FileUtility.saveContent(contentToSave);
        } catch (Exception e) {
            throw new DukeException("Unable to save content to file, " + e.getMessage());
        }
    }
}
