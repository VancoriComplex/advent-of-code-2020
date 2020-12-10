package day8.instruction;

public class InstructionFactory {

    public static Instruction createInstruction(String opCode, int argument) {
        switch (Operation.valueOfCode(opCode)) {
            case ACCUMULATE: return new Accumulate(Operation.ACCUMULATE, argument);
            case JUMP: return new Jump(Operation.JUMP, argument);
            default: return new NoOperation(Operation.NO_OPERATION, argument);
        }
    }
}
