package example.stopwatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class StopWatchTest {

    private StopWatch stopWatchUnderTest;

    @BeforeEach
    void setUp() {
        stopWatchUnderTest = new StopWatch();
    }

    void assertTime(int expectedMinutes, int expectedHours, int expectedDays) {
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(expectedMinutes);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(expectedHours);
        assertThat(stopWatchUnderTest.getDays()).isEqualTo(expectedDays);
    }

    @Test
    void shouldRecordMinutes() {
        stopWatchUnderTest.recordMinutes(10);
        assertTime(10, 0, 0);
    }

    @Test
    void shouldIgnoreNegativeInput() {
        stopWatchUnderTest.recordMinutes(-10);
        assertTime(0, 0, 0);
    }

    @Test
    void shouldRecordHoursWhenMinutesExceed60() {
        stopWatchUnderTest.recordMinutes(130);
        assertTime(10, 2, 0);
    }

    @Test
    void shouldRecordDaysWhenHoursExceed24() {
        stopWatchUnderTest.recordMinutes(3010);
        assertTime(10, 2, 2);
    }

    @Test
    void shouldBeAbleToRecordDaysBasedOnWorkHours() {
        stopWatchUnderTest.setWorkingHours(true);
        stopWatchUnderTest.recordMinutes(3010);
        assertTime(10, 2, 6);
    }

    @Test
    void shouldConvertFullDaysToWorkingDays() {
        stopWatchUnderTest.recordMinutes(3010);
        stopWatchUnderTest.setWorkingHours(true);
        assertTime(10, 2, 6);
    }

    @Test
    void shouldConvertWorkingDaysToFullDays() {
        stopWatchUnderTest.setWorkingHours(true);
        stopWatchUnderTest.recordMinutes(3010);
        stopWatchUnderTest.setWorkingHours(false);
        assertTime(10, 2, 2);
    }
}
