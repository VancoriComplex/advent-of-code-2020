package day8;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Interpreter {

    public int resolveInfiniteLoop(Map<Integer, Instruction> bootCode) {
        int accumulator = 0;
        int i = 0;
        Set<Instruction> visited = new HashSet<>();
        while (i < bootCode.size()) {
            Instruction instruction = bootCode.get(i);
            System.out.format("index: %d instruction: %s\n", i, instruction);
            if (visited.contains(instruction))
                return accumulator;
            else
                visited.add(instruction);
            switch (instruction.getOperation()) {
                case ACCUMULATE:
                    accumulator += instruction.getArgument();
                    break;
                case JUMP:
                    i += instruction.getArgument();
                    continue;
                case NO_OPERATION:
                    break;
            }
            i++;
        }
        return accumulator;
    }

    public int resolveCorruptedInstruction(Map<Integer, Instruction> bootCode) {
        return 0;
    }
}
