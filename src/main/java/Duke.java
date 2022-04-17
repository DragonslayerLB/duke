
import ViewController.DukeViewController;

public class Duke {
    static DukeViewController vc;

    public static void main(String[] args) {
        // main program entry point, inits and runs the view controller
        vc = new DukeViewController();
        vc.initialise();
        vc.run();
    }
}
