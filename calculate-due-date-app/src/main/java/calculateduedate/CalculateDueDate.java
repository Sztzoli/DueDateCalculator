package calculateduedate;

import java.time.*;
import java.time.temporal.TemporalAdjusters;


public class CalculateDueDate {

    public static final LocalTime WORK_TIME_START = LocalTime.of(9, 0);
    public static final LocalTime WORK_TIME_END = LocalTime.of(17, 0);

    public LocalDateTime CalculateDueDate(LocalDateTime dueDate, TurnAroundTime time) {
        validateDuaDate(dueDate);
        LocalDateTime nextFriday = nextFridayDateTime(dueDate);
        int numberOfDayToFriday = Period.between(dueDate.toLocalDate(), nextFriday.toLocalDate()).getDays();
        long numberOfMinutesToWorkEnd = Duration.between(dueDate, nextFriday).toMinutes() - numberOfDayToFriday * 16L * 60;

        long difference = time.getDuration() - numberOfMinutesToWorkEnd;
        return getResultDateTime(dueDate, time, difference);
    }

    private LocalDateTime getResultDateTime(LocalDateTime dueDate, TurnAroundTime time, long difference) {
        TimeConverter converter;
        if (difference <= 0) {
            converter = new TimeConverter(time.getDuration());
            return dueDate.plusDays(converter.getDays()).plusHours(converter.getHours()).plusMinutes(converter.getMinutes());
        } else {
            converter = new TimeConverter(difference);
            return nextMonday(dueDate).plusDays(converter.getDays()).plusHours(converter.getHours()).plusMinutes(converter.getMinutes());
        }
    }

    private LocalDateTime nextMonday(LocalDateTime time) {
        LocalDate monday = time.toLocalDate().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return LocalDateTime.of(monday, WORK_TIME_START);
    }

    private LocalDateTime nextFridayDateTime(LocalDateTime time) {
        LocalDate friday = time.toLocalDate().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        return LocalDateTime.of(friday, WORK_TIME_END);
    }

    private void validateDuaDate(LocalDateTime dueDate) {
        if (isWeekend(dueDate) || !isWorkHours(dueDate)) {
            throw new IllegalArgumentException("Invalid date/time: " + dueDate);
        }
    }


    private boolean isWeekend(LocalDateTime time) {
        DayOfWeek day = time.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private boolean isWorkHours(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(WORK_TIME_START) && !time.isAfter(WORK_TIME_END);
    }

}
