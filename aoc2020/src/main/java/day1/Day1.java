package day1;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Day1 {

    public static class Part1 {
        public static int processExpenseReport(String input, Integer expectedSum) {
            int[] inputNumbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> numbersCacheSet = new TreeSet<>();

            for (int i = 0; i < inputNumbers.length; i++) {
                int neededNumber = expectedSum - inputNumbers[i];
                if (neededNumber < 0) continue;
                if (numbersCacheSet.contains(inputNumbers[i]))
                    return inputNumbers[i]*neededNumber;
                numbersCacheSet.add(neededNumber);
            }
            return -1;
        }
    }

    public static class Part2 {
        public static int processExpenseReport(String input, Integer expectedSum) {

            int[] numbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> numbersCacheSet = new TreeSet<>();

            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    int neededNumber = expectedSum - numbers[i] - numbers[j];
                    if (neededNumber < 0) continue;
                    if (numbersCacheSet.contains(numbers[i]) && numbersCacheSet.contains(numbers[j]))
                        return numbers[i]*numbers[j]*neededNumber;
                    numbersCacheSet.add(neededNumber);
                }
            }
            return -1;
        }
    }
}
