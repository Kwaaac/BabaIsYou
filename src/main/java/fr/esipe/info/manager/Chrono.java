package fr.esipe.info.manager;

/**
 * Class that represent a chronometer
 */
public class Chrono {
    private long start;


    /**
     * Constructor. Set the start
     */
    public Chrono() {
        start = System.currentTimeMillis();
    }

    /**
     * Return the time passed since the start or reset, in Millisecond
     *
     * @return times passed in millisecond
     */
    public long getTimePassed() {
        return System.currentTimeMillis() - start;
    }

    /**
     * Reset the timer
     */
    public void reset() {
        start = System.currentTimeMillis();
    }
}
