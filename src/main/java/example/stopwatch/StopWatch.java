package example.stopwatch;

public class StopWatch {
    private int minutes = 0;

    public void recordMinutes(int minutes) {
        this.minutes += minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
