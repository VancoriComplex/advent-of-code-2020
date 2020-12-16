package day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day14 {

    public static long part1(List<String> input) {
        MaskSystem maskSystem = new MaskSystem();
        Map<Integer, Long> memory = new HashMap<>();
        String mask = "";
        for (String line : input) {
            if (lineStartsWith("mask", line)) {
                mask = getBitMask(line);
            } else {
                memory.put(parseAddressToInt(line),
                        maskSystem.decodeValue(getValue(line), mask));
            }
        }
        long sum = 0;
        for (Map.Entry<Integer, Long> address : memory.entrySet())
            sum += address.getValue();
        return sum;
    }

    public static long part2(List<String> input) {
        MaskSystem maskSystem = new MaskSystem();
        Map<Long, Integer> memory = new HashMap<>();
        String mask = "";

        for (String line : input) {
            if (lineStartsWith("mask", line)) {
                mask = getBitMask(line);
            } else {
                Set<Long> memoryCombinations = maskSystem.decodeMemoryAddress(parseAddressToString(line), mask);
                memoryCombinations.forEach(
                        address -> memory.put(address,
                                Integer.valueOf(getValue(line))));
            }
        }
        long sum = 0;
        for (Map.Entry<Long, Integer> address : memory.entrySet())
            sum += address.getValue();
        return sum;
    }

    private static boolean lineStartsWith(String mask, String line) {
        return line.split(" ")[0].equals(mask);
    }

    private static String getBitMask(String line) {
        return line.split(" ")[2];
    }

    private static String getValue(String line) {
        return line.split(" ")[2];
    }

    private static Integer parseAddressToInt(String line) {
        return Integer.valueOf(line.split(" ")[0].replaceAll("\\D", ""));
    }

    private static String parseAddressToString(String line) {
        return line.split(" ")[0].replaceAll("\\D", "");
    }
}
