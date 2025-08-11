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
        // Arrange/Act
        stopWatchUnderTest.recordMinutes(10);

        // Assert
        assertTime(10, 0, 0);
    }

    @Test
    void shouldIgnoreNegativeInput() {
        // Arrange/Act
        stopWatchUnderTest.recordMinutes(-10);

        // Assert
        assertTime(0, 0, 0);
    }

    @Test
    void shouldRecordHoursWhenMinutesExceed60() {
        // Arrange/Act
        stopWatchUnderTest.recordMinutes(130);

        // Assert
        assertTime(10, 2, 0);
    }

    @Test
    void shouldRecordDaysWhenHoursExceed24() {
        // Arrange/Act
        stopWatchUnderTest.recordMinutes(3010);

        // Assert
        assertTime(10, 2, 2);
    }

    @Test
    void shouldBeAbleToRecordDaysBasedOnWorkHours() {
        // Arrange
        stopWatchUnderTest.setWorkingHours(true);

        // Act
        stopWatchUnderTest.recordMinutes(3010);

        // Assert
        assertTime(10, 2, 6);
    }

    @Test
    void shouldConvertFullDaysToWorkingDays() {
        // Arrange
        stopWatchUnderTest.recordMinutes(3010);

        // Act
        stopWatchUnderTest.setWorkingHours(true);

        // Assert
        assertTime(10, 2, 6);
    }

    @Test
    void shouldConvertWorkingDaysToFullDays() {
        // Arrange
        stopWatchUnderTest.setWorkingHours(true);
        stopWatchUnderTest.recordMinutes(3010);

        // Act
        stopWatchUnderTest.setWorkingHours(false);

        // Assert
        assertTime(10, 2, 2);
    }
}
