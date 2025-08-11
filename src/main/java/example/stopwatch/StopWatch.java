package example.stopwatch;

public class StopWatch {
    private int minutes = 0;

    public void recordMinutes(int minutes) {
        if (minutes < 0) {
            return;
        }

        this.minutes += minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
