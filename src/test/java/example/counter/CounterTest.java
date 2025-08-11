package example.counter;

import org.junit.jupiter.api.Test;

public class CounterTest {
    @Test
    void shouldStartAtZero() {
        Counter counter = new Counter();
        assertThat(counter.getValue()).isEqualTo(0);
    }
}
