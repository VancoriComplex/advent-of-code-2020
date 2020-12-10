package day8.instruction;

import day8.program_state.ProgramState;

class NoOperation implements Instruction {

    private Operation operation;
    private final int argument;

    NoOperation(Operation operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    @Override
    public ProgramState calculateProgramState(ProgramState programState) {
        int accumulator = programState.getAccumulator();
        int index = programState.getCounter() + 1;
        return new ProgramState(accumulator, index);
    }

    @Override
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public int getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return String.format("%s : %d", operation, argument);
    }
}
