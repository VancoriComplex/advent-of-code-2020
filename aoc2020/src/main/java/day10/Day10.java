package day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

    public static class Part1 {

        public static int getJoltDifferencesCountMultiplied(List<Long> input) {
            Map<Long, Integer> joltDiffs = new HashMap<>();
            joltDiffs.put(1L, 0);
            joltDiffs.put(3L, 1);
            input.add(0L);
            input.sort(Long::compareTo);
            for (int i = 1; i <= input.size() - 1; i++) {
                joltDiffs.compute(input.get(i) - input.get(i - 1),
                        (key, value) -> (value == null) ? 1 : value + 1);
            }
            return joltDiffs.get(1L)*joltDiffs.get(3L);
        }
    }

    public static class Part2 {

        public static long getDistinctArrangementsCount(List<String> input) {
            return 0;
        }
    }
}
