package day8.resolver;

import day8.executor.Executor;
import day8.instruction.Instruction;
import day8.program_state.ProgramState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InfiniteLoopResolver implements Resolver {

    private final Executor executor;

    public InfiniteLoopResolver(Executor executor) {
        this.executor = executor;
    }

    @Override
    public int run(List<Instruction> code) {
        return runUntilLoopDetected(code);
    }

    private int runUntilLoopDetected(List<Instruction> code) {
        ProgramState programState = new ProgramState(0, 0);
        Set<Instruction> visited = new HashSet<>();
        while (programState.getCounter() < code.size()) {
            Instruction instruction = code.get(programState.getCounter());
            if (visited.contains(instruction))
                return programState.getAccumulator();
            visited.add(instruction);
            programState = executor.step(instruction, programState);
        }
        return programState.getAccumulator();
    }
}
