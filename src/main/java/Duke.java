import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Duke {
    // Region - Fields and Const
    private static ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_TASK_CAPACITY);

    static void processNextCommandFromUser() {
        String userInput = DukeProgrammeUtility.getNextLineOfInput().trim();

        try {
            if (userInput.equals(Commands.LIST)) {
                // process list command
                System.out.println(Constants.LIST_OF_TASKS_LINE + ":");
                DukeProgrammeUtility.printToConsoleEnumerated(tasks);
                System.out.println();
            } else if (DukeProgrammeUtility.isActionCommand(userInput)) {
                // mark task as done and print list
                ActionCommand command = DukeProgrammeUtility.processActionCommand(userInput);

                if (command.index - 1 >= tasks.size()) {
                    throw new DukeException("Task not found!");
                }

                switch (command.taskType) {
                    case MARK:
                        tasks.get(command.index - 1).isDone = true;
                        System.out.println(Constants.MARKED_AS_DONE_LINE + ":");
                        DukeProgrammeUtility.printToConsoleSingleTask(tasks.get(command.index - 1));
                        break;
                    case UNMARK:
                        tasks.get(command.index - 1).isDone = false;
                        System.out.println(Constants.UNMARKED_AS_DONE_LINE + ":");
                        DukeProgrammeUtility.printToConsoleSingleTask(tasks.get(command.index - 1));
                        break;
                    case DELETE:
                        System.out.println(Constants.TASK_REMOVED_LINE + ":");
                        DukeProgrammeUtility.printToConsoleSingleTask(tasks.get(command.index - 1));
                        tasks.remove(command.index - 1);
                        for (int i = command.index - 1; i < tasks.size(); i++) {
                            tasks.get(i).index = tasks.get(i).index - 1;
                        }
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println();
                        break;
                }
                System.out.println();
                DukeProgrammeUtility.saveTaskList(tasks);
            } else if (userInput.equals(Commands.BYE)) {
                // terminate program
                System.out.println(Constants.GOOD_BYE_LINE);
                return;
            } else {
                Task task = DukeProgrammeUtility.getTask(tasks.size() + 1, userInput);

                // echo command
                tasks.add(task);
                System.out.println(Constants.TASK_ADDED_LINE + ":");
                System.out.println("  " + task.getDescription());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println();
                DukeProgrammeUtility.saveTaskList(tasks);
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! " + e.description);
        }

        processNextCommandFromUser();
    }


    public static void main(String[] args) {
        try {
            FileUtility.initDb();
            System.out.println(Constants.GREETING_LINE);
            processNextCommandFromUser();
        } catch (Exception e) {
            System.out.println("unable to load db because: " + e.getMessage());
        }
    }
}
