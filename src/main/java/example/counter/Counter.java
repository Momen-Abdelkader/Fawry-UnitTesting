package example.counter;

public class Counter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void addToCount(int valueToCount) {
        count += valueToCount;
    }
}
