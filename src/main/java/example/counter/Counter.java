package example.counter;

public class Counter {
    int value = 0;

    public int getValue() {
        return value;
    }

    public void add(int value) {
        this.value += value;
    }
}
