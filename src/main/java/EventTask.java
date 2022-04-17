public class EventTask extends Task {
    String time;

    EventTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    String getDescription() {
        return "[E]" + super.getDescription() + "(at: " + time + ")";
    }

    @Override
    String getDbEntryDescription() {
        return "E | " + super.getDbEntryDescription() + " | " + time;
    }
}
