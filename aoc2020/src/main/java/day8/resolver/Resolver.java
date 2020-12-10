package day8.resolver;

import day8.instruction.Instruction;

import java.util.List;

public interface Resolver {
    int run(List<Instruction> code);
}
