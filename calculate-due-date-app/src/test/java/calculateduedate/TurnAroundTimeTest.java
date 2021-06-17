package calculateduedate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnAroundTimeTest {


    @Test
    void testInvalidHours() {
        assertThrows(IllegalArgumentException.class, () -> new TurnAroundTime(-1,21));
    }

    @Test
    void testInvalidMinutes() {
        assertThrows(IllegalArgumentException.class, ()-> new TurnAroundTime(34,-1));
        assertThrows(IllegalArgumentException.class, ()-> new TurnAroundTime(34,60));
    }

}