package calculateduedate;

public class TimeConverter {
    private long days;
    private long hours;
    private long minutes;

    public TimeConverter(long temp) {
        this.minutes = temp % 60;
        temp -= minutes;
        temp = temp / 60;
        this.hours = temp % 8;
        temp -= hours;
        this.days = temp / 8;
    }

    public long getDays() {
        return days;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }
}
