package day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InstructionReader {

    public Map<Integer, Instruction> parseCode(List<String> input) {
        Map<Integer, Instruction> result = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            result.put(i, parseInstruction(input.get(i)));
        }
        return result;
    }

    private Instruction parseInstruction(String line) {
        return new Instruction(parseOperation(line), parseArgument(line));
    }

    private Operation parseOperation(String line) {
        return Operation.valueOfCode(line.split(" ")[0]);
    }

    private int parseArgument(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }
}
