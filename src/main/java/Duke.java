import java.util.ArrayList;

public class Duke {
    // Region - Fields and Const
    private static ArrayList<String> tasks = new ArrayList<>(Constants.MAX_TASK_CAPACITY);

    static void processNextCommandFromUser() {
        String userInput = Utility.getNextLineOfInput().trim();

        // check for termination statement
        if (userInput.equals(Commands.BYE)) {
            System.out.println(Constants.GOOD_BYE_LINE);
            return;
        }

        if (userInput.equals(Commands.LIST)) {
            // process list command
            Utility.printToConsoleEnumerated(tasks);
        } else {
            // echo command
            tasks.add(userInput);
            System.out.println("added: " + userInput + "\n");
        }

        processNextCommandFromUser();
    }

    public static void main(String[] args) {
        System.out.println(Constants.GREETING_LINE);
        processNextCommandFromUser();
    }
}
