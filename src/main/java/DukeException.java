public class DukeException extends Exception {
    String description;

    DukeException(String description) {
        this.description = description;
    }
}
