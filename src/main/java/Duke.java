import javax.swing.text.Utilities;
import java.util.ArrayList;

public class Duke {
    // Region - Fields and Const
    private static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_TASK_CAPACITY);

    static void processNextCommandFromUser() {
        String userInput = DukeProgrammeUtility.getNextLineOfInput().trim();

        if (userInput.equals(Commands.LIST)) {
            // process list command
            System.out.println(Constants.LIST_OF_TASKS_LINE + ":");
            DukeProgrammeUtility.printToConsoleEnumerated(tasks);
            System.out.println();
        } else if (DukeProgrammeUtility.isMarkOrUnmarkCommand(userInput)) {
            // mark task as done and print list
            MarkingCommand command = DukeProgrammeUtility.processMarkCommand(userInput);
            tasks.get(command.index - 1).isDone = command.isMark;
            System.out.println((command.isMark ? Constants.MARKED_AS_DONE_LINE : Constants.UNMARKED_AS_DONE_LINE) + ":");
            DukeProgrammeUtility.printToConsoleSingleTask(tasks.get(command.index - 1));
            System.out.println();
        } else if (userInput.equals(Commands.BYE)) {
            // terminate program
            System.out.println(Constants.GOOD_BYE_LINE);
            return;
        } else {
            // echo command
            tasks.add(new Task(tasks.size() + 1, userInput));
            System.out.println("added: " + userInput);
            System.out.println();
        }

        processNextCommandFromUser();
    }


    public static void main(String[] args) {
        System.out.println(Constants.GREETING_LINE);
        processNextCommandFromUser();
    }
}
