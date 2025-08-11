package example.stopwatch;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class StopWatchTest {
    @Test
    void shouldRecordMinutes() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.recordMinutes(10);
        assertThat(stopWatch.getMinutes()).isEqualTo(10);
    }
}
