package example.stopwatch;

public class StopWatch {
    private int minutes = 0;
    private int hours = 0;
    private int days = 0;
    private int dayHours = 24;
    private boolean workHours;

    public void recordMinutes(int minutesToRecord) {
        if (minutesToRecord < 0) {
            return;
        }

        minutes += minutesToRecord;
        if (minutes > 60) {
            hours = minutes / 60;
            minutes -= 60 * hours;
        }

        if (hours > dayHours) {
            days = hours / dayHours;
            hours -= dayHours * days;
        }
    }

    public void setWorkingDays(boolean workHours) {
        this.workHours = workHours;
        dayHours = workHours ? 8 : 24;
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
