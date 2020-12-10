package day8.instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionReader {

    public List<Instruction> parseCode(List<String> input) {
        List<Instruction> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            result.add(parseInstruction(input.get(i)));
        }
        return result;
    }

    private Instruction parseInstruction(String line) {
        return InstructionFactory.createInstruction(parseOperation(line), parseArgument(line));
    }

    private String parseOperation(String line) {
        return line.split(" ")[0];
    }

    private int parseArgument(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }
}
