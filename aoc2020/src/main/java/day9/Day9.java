package day9;

import java.util.*;

public class Day9 {

    public static class Part1 {

        public static long getFirstInvalidNumber(List<Long> input, int preambleSize) {
            Set<Long> preamble = getPreamble(input, preambleSize);
            for (int i = preambleSize; i < input.size(); i++) {
                if (!isValid(input.get(i), preamble)) {
                    return input.get(i);
                }
                updatePreamble(input.get(i - preambleSize), input.get(i), preamble);
            }
            return -1;
        }

        private static Set<Long> getPreamble(List<Long> input, int preambleSize) {
            Set<Long> result = new HashSet<>();
            for (int i = 0; i < preambleSize; i++) {
                result.add(input.get(i));
            }
            return result;
        }

        private static boolean isValid(Long sum, Set<Long> preamble) {
            for (Long preNum : preamble) {
                if (preamble.contains(sum - preNum))
                    return true;
            }
            return false;
        }

        private static void updatePreamble(Long oldNum, Long newNum, Set<Long> preamble) {
            preamble.remove(oldNum);
            preamble.add(newNum);
        }
    }

    public static class Part2 {

        public static long getConsecutiveMinMaxSum(List<Long> input, Long invalid) {
            Set<Long> consecutive = new HashSet<>();
            int index = 0;
            int consecutiveStartIndex = 0;
            int invalidIndex = input.indexOf(invalid);
            long consecutiveSum = invalid;

            while (consecutiveSum > 0 && index < invalidIndex) {
                consecutiveSum -= input.get(index);
                consecutive.add(input.get(index));
                index++;
                if (consecutiveSum == 0) {
                    return Collections.min(consecutive) + Collections.max(consecutive);
                }
                if (consecutiveSum < 0) {
                    consecutiveStartIndex++;
                    consecutiveSum = invalid;
                    consecutive.clear();
                    index = consecutiveStartIndex;
                }
            }
            return -1;
        }
    }
}
