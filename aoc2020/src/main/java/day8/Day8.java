package day8;

import java.util.List;
import java.util.Map;

public class Day8 {

    private static InstructionReader reader = new InstructionReader();
    private static Interpreter interpreter = new Interpreter();

    public static class Part1 {

        public static int processInput(List<String> input) {
            Map<Integer, Instruction> bootCode = reader.parseCode(input);
            return interpreter.resolveInfiniteLoop(bootCode);
        }
    }

    public static class Part2 {

        public static int processInput(List<String> input) {
            Map<Integer, Instruction> bootCode = reader.parseCode(input);
            return interpreter.resolveCorruptedInstruction(bootCode);
        }
    }
}
