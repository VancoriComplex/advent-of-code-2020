package day8;

import day8.executor.Executor;
import day8.instruction.Instruction;
import day8.instruction.InstructionReader;
import day8.resolver.CorruptedOperationResolver;
import day8.resolver.InfiniteLoopResolver;
import day8.resolver.Resolver;

import java.util.List;

public class Day8 {

    public static class Part1 {

        public static int processInput(List<String> input) {
            Executor executor = new Executor();
            Resolver infiniteLoopResolver = new InfiniteLoopResolver(executor);
            InstructionReader reader = new InstructionReader();
            List<Instruction> bootCode = reader.parseCode(input);
            return infiniteLoopResolver.run(bootCode);
        }
    }

    public static class Part2 {


        public static int processInput(List<String> input) {
            Executor executor = new Executor();
            Resolver corruptionResolver = new CorruptedOperationResolver(executor);
            InstructionReader reader = new InstructionReader();
            List<Instruction> bootCode = reader.parseCode(input);
            return corruptionResolver.run(bootCode);
        }
    }
}
