public class DeadlineTask extends Task {
    String time;

    DeadlineTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    String getDescription() {
        return "[D]" + super.getDescription() + "(by: " + time + ")";
    }
}
