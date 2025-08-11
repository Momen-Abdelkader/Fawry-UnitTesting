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
    void shouldRecordHours() {
        stopWatchUnderTest.recordMinutes(70);
        assertThat(stopWatchUnderTest.getMinutes()).isEqualTo(10);
        assertThat(stopWatchUnderTest.getHours()).isEqualTo(1);
    }
}
