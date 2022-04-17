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

    public static Boolean isMarkOrUnmarkCommand(String commandStr) {
        String[] subStr = commandStr.split(" ");
        try {
            if (subStr.length == 2) {
                int index = Integer.parseInt(subStr[1]);
                return subStr[0].equals(Commands.MARK) || subStr[0].equals(Commands.UNMARK);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static MarkingCommand processMarkCommand(String commandStr) {
        String[] subStr = commandStr.split(" ");
        return new MarkingCommand(subStr[0].equals(Commands.MARK), Integer.parseInt(subStr[1]));
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

    public static Task getTask(int index, String commandStr) throws Exception {
        String[] words = commandStr.split(" ");

        String description = "";
        String time = "";
        if (words[0].equals(Commands.TODO)) {
            for (int i = 1; i < words.length; i++) {
                description += words[i];

                if (i != words.length-1) {
                    description += " ";
                }
            }

            return new TODOTask(index, description);
        } else if (words[0].equals(Commands.EVENT) || words[0].equals(Commands.DEADLINE)) {
            int atIndex = -1;
            for (int i = 1; i < words.length; i++) {
                if (words[i].equals(words[0].equals(Commands.EVENT) ? "/at" : "/by")) {
                    atIndex = i;
                    break;
                }
                description += words[i];
                description += " ";
            }

            if (atIndex + 1 >= words.length || atIndex == -1) {
                throw new Exception("failed");
            }

            for (int i = atIndex + 1; i < words.length; i++) {
                time += words[i];

                if (i != words.length - 1) {
                    time += " ";
                }
            }

            return new EventTask(index, description, time);
        }

        throw new Exception("failed");
    }
}
