package day15;

import java.util.*;

public class Day15 {


    public static int part12(List<Integer> input) {
        Map<Integer, int[]> memory = new HashMap<>();
        for (Integer v : input) {
            memory.put(v, new int[] {0, input.indexOf(v) + 1});
        }
        int next = 0;
        for (int i = input.size() + 1; i < 30000000; i++) {
            if (memory.containsKey(next)) {
                memory.put(next, new int[] {memory.get(next)[1], i});
                next = memory.get(next)[1] - memory.get(next)[0];
            } else {
                memory.put(next, new int[] {0, i});
                next = 0;
            }
        }
        return next;
    }
}
