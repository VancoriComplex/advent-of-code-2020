package day8;

import java.util.List;
import java.util.Map;

public class Day8 {

    private static InstructionReader reader = new InstructionReader();

    public static class Part1 {

        private static Interpreter infiniteLoopResolver = new InfiniteLoopResolver();

        public static int processInput(List<String> input) {
            Map<Integer, Instruction> bootCode = reader.parseCode(input);
            return infiniteLoopResolver.run(bootCode);
        }
    }

    public static class Part2 {

        private static Interpreter corruptionResolver = new CorruptedOperationResolver();

        public static int processInput(List<String> input) {
            Map<Integer, Instruction> bootCode = reader.parseCode(input);
            return corruptionResolver.run(bootCode);
        }
    }
}
