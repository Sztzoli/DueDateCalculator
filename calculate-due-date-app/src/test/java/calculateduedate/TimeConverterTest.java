package calculateduedate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeConverterTest {


    @DisplayName("Test only minutes")
    @Test
    void testTimeConverter() {
        TimeConverter converter = new TimeConverter(59);
        assertEquals(59, converter.getMinutes());
        assertEquals(0, converter.getDays());
        assertEquals(0, converter.getHours());
    }

    @DisplayName("Test only minutes and hours")
    @Test
    void testTimeConverter2() {
        TimeConverter converter = new TimeConverter(61);
        assertEquals(1, converter.getMinutes());
        assertEquals(1, converter.getHours());
        assertEquals(0, converter.getDays());
    }

    @DisplayName("Test All")
    @Test
    void testTimeConverter3() {
        TimeConverter converter = new TimeConverter(541);
        assertEquals(1, converter.getMinutes());
        assertEquals(1, converter.getHours());
        assertEquals(1, converter.getDays());
    }



}