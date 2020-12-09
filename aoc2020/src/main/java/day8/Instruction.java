package day8;

class Instruction {

    private Operation operation;
    private int argument;

    public Instruction(Operation operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return String.format("%s %d", operation, argument);
    }
}
