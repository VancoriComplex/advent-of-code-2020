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
            switch (instruction.getOperation()) {
                case ACCUMULATE:
                    accumulator += instruction.getArgument();
                    break;
                case JUMP:
                    index += instruction.getArgument();
                    continue;
                case NO_OPERATION:
                    break;
            }
            index++;
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

    private Map<Integer, Instruction> switchOperation(int potentiallyCorrupted, Map<Integer, Instruction> bootCode) {
        Instruction toSwitch = bootCode.get(potentiallyCorrupted);
        switch (toSwitch.getOperation()) {
            case JUMP:
                toSwitch.setOperation(Operation.NO_OPERATION);
                bootCode.put(potentiallyCorrupted, toSwitch);
                return bootCode;
            case NO_OPERATION:
                toSwitch.setOperation(Operation.JUMP);
                bootCode.put(potentiallyCorrupted, toSwitch);
                return bootCode;
        }
        return bootCode;
    }
}
