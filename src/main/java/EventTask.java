import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    String time;

    EventTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    String getDescription() {
        return "[E]" + super.getDescription() + "(at: " + getFormattedLocalTime() + ")";
    }

    @Override
    String getDbEntryDescription() {
        return "E | " + super.getDbEntryDescription() + " | " + time;
    }

    String getFormattedLocalTime() {
        return DukeProgrammeUtility.attemptToConvertTimeString(time);
    }
}
