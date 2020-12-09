package day8;

import java.util.List;
import java.util.Map;

interface CodeParser {
    Map<Integer, Instruction> parseCode(List<String> input);
}
