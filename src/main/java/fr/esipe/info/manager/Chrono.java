package fr.esipe.info.manager;

public class Chrono {
    private long start;

    public Chrono() {
        start = System.currentTimeMillis();
    }

    public long getTimePassed() {
        return System.currentTimeMillis() - start;
    }

    public void reset() {
        start = System.currentTimeMillis();
    }
}
