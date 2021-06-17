package calculateduedate;

public class TurnAroundTime {

    private int hours;
    private int minutes;

    public TurnAroundTime(int hours, int minutes) {
        validateMinutes(minutes);
        validateHours(hours);
        this.hours = hours;
        this.minutes = minutes;
    }


    public long getDuration() {
        return hours * 60L + minutes;
    }

    private void validateHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("hours must be positive: " + hours);
        }
    }

    private void validateMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("minutes must be between 0-59: " + minutes);
        }
    }

    @Override
    public String toString() {
        return "TurnAroundTime{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}
