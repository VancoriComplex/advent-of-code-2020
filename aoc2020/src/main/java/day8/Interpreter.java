package day8;

import java.util.Map;

interface Interpreter {
    int run(Map<Integer, Instruction> code);
}
