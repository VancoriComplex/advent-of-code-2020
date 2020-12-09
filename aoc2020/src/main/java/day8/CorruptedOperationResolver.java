package day8;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CorruptedOperationResolver implements Interpreter {

    @Override
    public int run(Map<Integer, Instruction> code) {
        return resolveCorruptedInstruction(code);
    }

    public int resolveCorruptedInstruction(Map<Integer, Instruction> code) {
        int accumulator = 0;
        int index = 0;
        Set<Instruction> visited = new HashSet<>();
        int potentiallyCorrupted = findNextPotentiallyCorrupted(code, code.size());
        switchOperation(potentiallyCorrupted, code);
        while (index < code.size()) {
            Instruction instruction = code.get(index);
            if (visited.contains(instruction)) {
                accumulator = 0;
                index = 0;
                visited.clear();
                switchOperation(potentiallyCorrupted, code);
                potentiallyCorrupted = findNextPotentiallyCorrupted(code, potentiallyCorrupted);
                switchOperation(potentiallyCorrupted, code);
                continue;
            }
            visited.add(instruction);
            accumulator = instruction.calculateAccumulator(accumulator);
            index = instruction.calculateOffset(index);
        }
        return accumulator;
    }

    private int findNextPotentiallyCorrupted(Map<Integer, Instruction> code, int size) {
        for (int i = size - 1; i >= 0; i--) {
            Operation operation = code.get(i).getOperation();
            if (operation.equals(Operation.JUMP)
                    || operation.equals(Operation.NO_OPERATION))
                return i;
        }
        return -1;
    }

    private Map<Integer, Instruction> switchOperation(int potentiallyCorrupted, Map<Integer, Instruction> code) {
        Instruction toSwitch = code.get(potentiallyCorrupted);
        switch (toSwitch.getOperation()) {
            case JUMP:
                toSwitch.setOperation(Operation.NO_OPERATION);
                code.put(potentiallyCorrupted, toSwitch);
                return code;
            case NO_OPERATION:
                toSwitch.setOperation(Operation.JUMP);
                code.put(potentiallyCorrupted, toSwitch);
                return code;
        }
        return code;
    }
}
