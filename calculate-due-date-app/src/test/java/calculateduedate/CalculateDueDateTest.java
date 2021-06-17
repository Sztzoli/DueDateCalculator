package calculateduedate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculateDueDateTest {

    CalculateDueDate calculateDueDate;

    @BeforeEach
    void setUp() {
        calculateDueDate = new CalculateDueDate();
    }

    @ParameterizedTest(name = "duaDate: {0} - TurnAroundTime: {1} - expectedDate: {2}")
    @MethodSource("calculate")
    void testCalculateDueDate(LocalDateTime dueDate, TurnAroundTime time, LocalDateTime expected) {
        assertEquals(expected, calculateDueDate.CalculateDueDate(dueDate, time));
    }

    static Stream<Arguments> calculate() {
        return Stream.of(
                arguments(
                        LocalDateTime.of(2021, Month.JUNE, 17, 9, 0),
                        new TurnAroundTime(4, 30),
                        LocalDateTime.of(2021, Month.JUNE, 17, 13, 30)),
                arguments(
                        LocalDateTime.of(2021, Month.JUNE, 17, 9, 0),
                        new TurnAroundTime(8, 30),
                        LocalDateTime.of(2021, Month.JUNE, 18, 9, 30)),
                arguments(
                        LocalDateTime.of(2021, Month.JUNE, 17, 9, 0),
                        new TurnAroundTime(16, 30),
                        LocalDateTime.of(2021, Month.JUNE, 21, 9, 30))
        );
    }

    @DisplayName("Before work start")
    @Test
    void testInValidDueDate1() {
        LocalDateTime testTime = LocalDateTime.of(2021, Month.JUNE, 17, 8, 59);
        Exception ex =
                assertThrows(IllegalArgumentException.class,
                        () -> calculateDueDate.CalculateDueDate(
                                testTime,
                                new TurnAroundTime(4, 30)));
        assertEquals("Invalid date/time: 2021-06-17T08:59", ex.getMessage());
    }

    @DisplayName("After work start")
    @Test
    void testInValidDueDate2() {
        LocalDateTime testTime = LocalDateTime.of(2021, Month.JUNE, 17, 17, 1);

        assertThrows(IllegalArgumentException.class,
                () -> calculateDueDate.CalculateDueDate(
                        testTime,
                        new TurnAroundTime(4, 30)));

    }

    @DisplayName("Day is Saturday")
    @Test
    void testInValidDueDate3() {
        LocalDateTime testTime = LocalDateTime.of(2021, Month.JUNE, 19, 17, 1);

        assertThrows(IllegalArgumentException.class,
                () -> calculateDueDate.CalculateDueDate(
                        testTime,
                        new TurnAroundTime(4, 30)));

    }

}