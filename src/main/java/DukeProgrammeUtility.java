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
            System.out.println((i + 1) + "." + getFormattedTaskDescription(task));
        }
    }
}
