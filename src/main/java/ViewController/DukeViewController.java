package ViewController;

import Command.ActionCommand;
import Command.FindCommand;
import Exceptions.DukeException;
import Tasks.Task;
import UI.ConsoleUIManager;
import UI.UIManager;
import Util.FileHelper;
import Util.Parser;
import Constants.Commands;
import Constants.Constants;

import java.util.ArrayList;

public class DukeViewController implements ViewController {
    // Region - Fields and Const
    private ArrayList<Task> tasks = new ArrayList<>(Constants.MAX_TASK_CAPACITY);
    private UIManager uiManager;

    public void initialise() {
        try {
            uiManager = new ConsoleUIManager();
            FileHelper.initDb();
            uiManager.displayText((Constants.GREETING_LINE));
        } catch (Exception e) {
            uiManager.displayText(("unable to load db because: " + e.getMessage()));
            uiManager.displayText("");
        }
    }

    public void run() {
        String userInput = Parser.getNextLineOfInput().trim();

        try {
            if (userInput.equals(Commands.LIST)) {
                // process list command
                uiManager.displayText(Constants.LIST_OF_TASKS_LINE + ":");
                uiManager.listEnumeratedTaskDescriptions(tasks);
                uiManager.displayText("");
            } else if (Parser.validateFindCommand(userInput)) {
                // handle find command
                ArrayList<Task> foundTasks = new ArrayList<>();
                FindCommand command = Parser.processFindCommand(userInput);
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).description.contains(command.key)) {
                        foundTasks.add(tasks.get(i));
                    }
                }

                uiManager.displayText((Constants.TASK_FOUND_LINE + ":"));
                uiManager.listEnumeratedTaskDescriptions(foundTasks);
                uiManager.displayText("");

            } else if (Parser.validateActionCommand(userInput)) {
                // mark task as done and print list
                ActionCommand command = Parser.processActionCommand(userInput);

                if (command.index - 1 >= tasks.size()) {
                    throw new DukeException("Task not found!");
                }

                switch (command.taskType) {
                    case MARK:
                        tasks.get(command.index - 1).isDone = true;
                        uiManager.displayText((Constants.MARKED_AS_DONE_LINE + ":"));
                        uiManager.listSingleTaskDescription(tasks.get(command.index - 1));
                        break;
                    case UNMARK:
                        tasks.get(command.index - 1).isDone = false;
                        uiManager.displayText((Constants.UNMARKED_AS_DONE_LINE + ":"));
                        uiManager.listSingleTaskDescription(tasks.get(command.index - 1));
                        break;
                    case DELETE:
                        uiManager.displayText((Constants.TASK_REMOVED_LINE + ":"));
                        uiManager.listSingleTaskDescription(tasks.get(command.index - 1));
                        tasks.remove(command.index - 1);
                        for (int i = command.index - 1; i < tasks.size(); i++) {
                            tasks.get(i).index = tasks.get(i).index - 1;
                        }
                        uiManager.displayText(("Now you have " + tasks.size() + " tasks in the list."));
                        uiManager.displayText("");
                        break;
                }
                uiManager.displayText("");
                FileHelper.saveTaskList(tasks);
            } else if (userInput.equals(Commands.BYE)) {
                // terminate program
                uiManager.displayText(Constants.GOOD_BYE_LINE);
                return;
            } else {
                Task task = Parser.getTask(tasks.size() + 1, userInput);
                for (int i = 0; i < tasks.size(); i++) {
                    if (task.isEqualTo(tasks.get(i))) {
                        uiManager.displayText(("Duplicated"));
                        run();
                        return;
                    }
                }

                // echo command
                tasks.add(task);
                uiManager.displayText((Constants.TASK_ADDED_LINE + ":"));
                uiManager.displayText(("  " + task.getDescription()));
                uiManager.displayText(("Now you have " + tasks.size() + " tasks in the list."));
                uiManager.displayText("");
                FileHelper.saveTaskList(tasks);
            }
        } catch (DukeException e) {
            uiManager.displayText(("☹ OOPS!!! " + e.description));
            uiManager.displayText("");
        }

        run();
    }

    public DukeViewController() {

    }
}
