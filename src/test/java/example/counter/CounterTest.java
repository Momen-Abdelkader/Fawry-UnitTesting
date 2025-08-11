package example.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CounterTest {

    private Counter counterUnderTest;

    @BeforeEach
    void setUp() {
        counterUnderTest = new Counter();
    }

    @Test
    void shouldStartAtZero() {
        assertThat(counterUnderTest.getCount()).isEqualTo(0);
    }

    @Test
    void shouldAddToCount() {
        counterUnderTest.addToCount(5);
        counterUnderTest.addToCount(3);
        assertThat(counterUnderTest.getCount()).isEqualTo(8);
    }
}
