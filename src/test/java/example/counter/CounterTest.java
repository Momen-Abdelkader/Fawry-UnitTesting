package example.counter;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CounterTest {

    @Test
    void shouldStartAtZero() {
        Counter counter = new Counter();
        assertThat(counter.getValue()).isEqualTo(0);
    }

    @Test
    void shouldAccumulateValues() {
        Counter counter = new Counter();
        counter.add(5);
        counter.add(3);
        assertThat(counter.getValue()).isEqualTo(8);
    }
}
