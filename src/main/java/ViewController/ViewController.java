package ViewController;

public interface ViewController {
    /*
     * Initialises the current view controller
     * */
    void initialise();

    /*
     * Triggers the run loop, should be called only once
     * */
    void run();
}
