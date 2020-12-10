package day8.executor;

import day8.instruction.Instruction;
import day8.program_state.ProgramState;

public class Executor {
    public ProgramState step(Instruction instruction, ProgramState programState) {
        return instruction.calculateProgramState(programState);
    }
}
