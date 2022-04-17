package Tasks;

public class Task {
    public int index;
    public String description;
    public Boolean isDone = false;

    protected Task(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public String getDescription() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getDbEntryDescription() {
        return (isDone ? 1 : 0) + " | " + description;
    }

    public String getIndexedDescription() {
        return index + "." + getDescription();
    }

    public Boolean isEqualTo(Task task) {
        return description.equals(task.description);
    }
}
