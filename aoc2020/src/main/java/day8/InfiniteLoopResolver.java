package day8;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class InfiniteLoopResolver implements Interpreter {

    @Override
    public int run(Map<Integer, Instruction> code) {
        return resolveInfiniteLoop(code);
    }

    private int resolveInfiniteLoop(Map<Integer, Instruction> code) {
        int accumulator = 0;
        int i = 0;
        Set<Instruction> visited = new HashSet<>();
        while (i < code.size()) {
            Instruction instruction = code.get(i);
            if (visited.contains(instruction))
                return accumulator;
            visited.add(instruction);
            accumulator = instruction.calculateAccumulator(accumulator);
            i = instruction.calculateOffset(i);
        }
        return accumulator;
    }
}
