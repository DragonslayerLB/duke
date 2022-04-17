public class TODOTask extends Task {
    TODOTask(int index, String description) {
        super(index, description);
    }

    @Override
    String getDescription() {
        return "[T]" + super.getDescription();
    }
}