import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    String time;

    DeadlineTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    String getDescription() {
        return "[D]" + super.getDescription() + "(by: " + getFormattedLocalTime() + ")";
    }

    @Override
    String getDbEntryDescription() {
        return "D | " + super.getDbEntryDescription() + " | " + time;
    }

    String getFormattedLocalTime() {
        return DukeProgrammeUtility.attemptToConvertTimeString(time);
    }
}
