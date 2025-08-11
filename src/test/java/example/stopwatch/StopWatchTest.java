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

    @Test
    void shouldRecordMinutes() {
        stopWatchUnderTest.recordMinutes(10);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
    }

    @Test
    void shouldIgnoreNegativeInput() {
        stopWatchUnderTest.recordMinutes(-10);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(0);
    }

    @Test
    void shouldRecordHoursWhenMinutesExceed60() {
        stopWatchUnderTest.recordMinutes(130);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(2);
    }

    @Test
    void shouldRecordDaysWhenHoursExceed24() {
        stopWatchUnderTest.recordMinutes(3010);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(2);
        assertThat(stopWatchUnderTest.getDays()).isEqualTo(2);
    }

    @Test
    void shouldBeAbleToRecordDaysBasedOnWorkHours() {
        stopWatchUnderTest.setWorkingHours(true);
        stopWatchUnderTest.recordMinutes(3010);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(2);
        assertThat(stopWatchUnderTest.getDays()).isEqualTo(6);
    }

    @Test
    void shouldConvertExistingDaysAccordingToDailyHours() {
        stopWatchUnderTest.recordMinutes(3010); // recorded based on 24-hour days
        stopWatchUnderTest.setWorkingHours(true);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(2);
        assertThat(stopWatchUnderTest.getDays()).isEqualTo(6);

        stopWatchUnderTest.setWorkingHours(false);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(2);
        assertThat(stopWatchUnderTest.getDays()).isEqualTo(2);
    }
}
