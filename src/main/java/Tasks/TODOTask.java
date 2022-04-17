package Tasks;

public class TODOTask extends Task {
    public TODOTask(int index, String description) {
        super(index, description);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

    @Override
    public String getDbEntryDescription() {
        return "T | " + super.getDbEntryDescription();
    }
}