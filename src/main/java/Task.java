public class Task {
    int index;
    String description;
    Boolean isDone = false;

    Task(int index, String description) {
        this.index = index;
        this.description = description;
    }

    String getDescription() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    String getIndexedDescription() {
        return index + "." + getDescription();
    }
}
