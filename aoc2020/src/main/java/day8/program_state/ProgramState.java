package day8.program_state;

public class ProgramState {

    private int accumulator;
    private int counter;

    public ProgramState(int accumulator, int counter) {
        this.accumulator = accumulator;
        this.counter = counter;
    }

    public int getAccumulator() {
        return accumulator;
    }

    public int getCounter() {
        return counter;
    }

    public void reset() {
        this.accumulator = 0;
        this.counter = 0;
    }

    @Override
    public String toString() {
        return String.format("acc: %d, i: %d", accumulator, counter);
    }
}
