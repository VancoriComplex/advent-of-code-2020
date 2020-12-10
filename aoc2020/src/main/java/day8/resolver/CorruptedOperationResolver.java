package day8.resolver;

import day8.executor.Executor;
import day8.instruction.Instruction;
import day8.instruction.InstructionFactory;
import day8.instruction.Operation;
import day8.program_state.ProgramState;

import java.util.*;

public class CorruptedOperationResolver implements Resolver {

    private static final Map<Operation, Operation> OPERATION_SWITCH_MAP = new HashMap<>();

    static {
        OPERATION_SWITCH_MAP.put(Operation.NO_OPERATION, Operation.JUMP);
        OPERATION_SWITCH_MAP.put(Operation.JUMP, Operation.NO_OPERATION);
    }

    private final Executor executor;

    public CorruptedOperationResolver(Executor executor) {
        this.executor = executor;
    }

    @Override
    public int run(List<Instruction> code) {
        return fixCorruptedInstruction(code);
    }

    private int fixCorruptedInstruction(List<Instruction> code) {
        ProgramState programState = new ProgramState(0, 0);
        Set<Instruction> visited = new HashSet<>();
        int potentiallyCorrupted = findNextPotentiallyCorrupted(code, code.size());
        switchOperation(potentiallyCorrupted, code);
        while (programState.getCounter() < code.size()) {
            Instruction instruction = code.get(programState.getCounter());
            if (visited.contains(instruction)) {
                programState.reset();
                visited.clear();
                switchOperation(potentiallyCorrupted, code);
                potentiallyCorrupted = findNextPotentiallyCorrupted(code, potentiallyCorrupted);
                switchOperation(potentiallyCorrupted, code);
                continue;
            }
            visited.add(instruction);
            programState = executor.step(instruction, programState);
        }
        return programState.getAccumulator();
    }

    private int findNextPotentiallyCorrupted(List<Instruction> code, int end) {
        for (int i = end - 1; i >= 0; i--) {
            Operation operation = code.get(i).getOperation();
            if (operation.equals(Operation.JUMP) || operation.equals(Operation.NO_OPERATION))
                return i;
        }
        return -1;
    }

    private List<Instruction> switchOperation(int potentiallyCorrupted, List<Instruction> code) {
        Instruction toSwitch = code.get(potentiallyCorrupted);
        Operation switchedOp = OPERATION_SWITCH_MAP.get(toSwitch.getOperation());
        toSwitch = InstructionFactory.createInstruction(
                        switchedOp.getCode(),
                        toSwitch.getArgument());
        code.set(potentiallyCorrupted, toSwitch);
        return code;
    }
}
