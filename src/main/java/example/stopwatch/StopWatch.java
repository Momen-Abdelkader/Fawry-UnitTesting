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

    public void setWorkingHours(boolean workHours) {
        if (this.workHours == workHours) {
            return;
        }

        this.workHours = workHours;
        if (workHours) {
            minutes += (days * 24 + hours) * 60;
            dayHours = 8;
        }
        else {
            minutes += (days * 8 + hours) * 60;
            dayHours = 24;
        }

        calculateHours();
        calculateDays();
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

    private void calculateHours() {
        if (minutes > 60) {
            hours = minutes / 60;
            minutes -= 60 * hours;
        }
    }

    private void calculateDays() {
        if (hours > dayHours) {
            days = hours / dayHours;
            hours -= dayHours * days;
        }
    }
}
