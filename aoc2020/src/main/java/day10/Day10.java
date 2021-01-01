package day10;

import java.util.*;

public class Day10 {

    private static Map<Integer, Long> seen = new LinkedHashMap<>();

    public static int part1(List<Long> input) {
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

    public static long part2(List<Integer> input) {
        input.add(0);
        input.sort(Integer::compareTo);
        Map<Integer, Set<Integer>> adapters = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            Set<Integer> matchingAdapters = getMatchingAdapters(input.get(i), input);
            adapters.put(input.get(i), matchingAdapters);
        }

        return getArrangementsCount(0, adapters);
    }

    private static Set<Integer> getMatchingAdapters(Integer adapter, List<Integer> input) {
        Set<Integer> matchingAdapters = new HashSet<>();
        for (int i = input.indexOf(adapter) + 1; i < input.size(); i++) {
            if (input.get(i) - adapter > 3)
                break;
            else
                matchingAdapters.add(input.get(i));
        }
        return matchingAdapters;
    }

    private static long getArrangementsCount(Integer adapter, Map<Integer, Set<Integer>> adapters) {
        if (adapters.get(adapter).size() == 0)
            return 1;
        if (seen.containsKey(adapter))
            return seen.get(adapter);
        long arrangements = 0;
        for (Integer matchingAdapter : adapters.get(adapter))
            arrangements += getArrangementsCount(matchingAdapter, adapters);
        seen.put(adapter, arrangements);
        return arrangements;
    }
}
