package day8;

class Instruction {

    private Operation operation;
    private int argument;

    public Instruction(Operation operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return String.format("%s %d", operation, argument);
    }

    public int calculateAccumulator(int accumulator) {
        switch (operation) {
            case ACCUMULATE:
                return accumulator + argument;
            default:
                return accumulator;
        }
    }

    public int calculateOffset(int i) {
        switch (operation) {
            case JUMP:
                return i + argument;
            default:
                return i + 1;
        }
    }
}
