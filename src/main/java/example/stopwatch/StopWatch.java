package example.stopwatch;

public class StopWatch {
    private int minutes = 0;
    private int hours = 0;
    private int days = 0;

    public void recordMinutes(int minutesToRecord) {
        if (minutesToRecord < 0) {
            return;
        }

        minutes += minutesToRecord;
        if (minutes > 60) {
            hours = minutes / 60;
            minutes -= 60 * hours;
        }

        if (hours > 24) {
            days = hours / 24;
            hours -= 24 * days;
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getDays() {
        return days;
    }
}
