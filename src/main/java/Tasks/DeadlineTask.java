package Tasks;
import Util.Parser;

public class DeadlineTask extends Task {
    String time;

    public DeadlineTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(by: " + getFormattedLocalTime() + ")";
    }

    @Override
    public String getDbEntryDescription() {
        return "D | " + super.getDbEntryDescription() + " | " + time;
    }

    String getFormattedLocalTime() {
        return Parser.attemptToConvertTimeString(time);
    }
}
