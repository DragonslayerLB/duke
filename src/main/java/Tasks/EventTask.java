package Tasks;

import Util.Parser;

public class EventTask extends Task {
    String time;

    public EventTask(int index, String description, String time) {
        super(index, description);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + "(at: " + getFormattedLocalTime() + ")";
    }

    @Override
    public String getDbEntryDescription() {
        return "E | " + super.getDbEntryDescription() + " | " + time;
    }

    String getFormattedLocalTime() {
        return Parser.attemptToConvertTimeString(time);
    }
}
