package day8;

import java.util.HashMap;
import java.util.Map;

enum Operation {
    ACCUMULATE("acc"),
    JUMP("jmp"),
    NO_OPERATION("nop");

    private static final Map<String, Operation> BY_CODE = new HashMap<>();

    static {
        for (Operation op : values())
            BY_CODE.put(op.code, op);
    }

    private final String code;

    public static Operation valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    Operation(String code) {
        this.code = code;
    }
}
