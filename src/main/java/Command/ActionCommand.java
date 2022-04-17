package Command;

import Constants.TaskType;

public class ActionCommand {
    public TaskType taskType;
    public int index;

    public ActionCommand(TaskType taskType, int index) {
        this.taskType = taskType;
        this.index = index;
    }
}
