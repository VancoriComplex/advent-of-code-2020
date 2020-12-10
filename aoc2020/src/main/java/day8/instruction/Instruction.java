package day8.instruction;

import day8.program_state.ProgramState;

public interface Instruction {
    ProgramState calculateProgramState(ProgramState programState);
    void setOperation(Operation operation);
    Operation getOperation();
    int getArgument();
}
