package day1;

public class Day1 {

    public static class Part1 {
        public static int processExpenseReport(String input, Integer expectedSum) {
            String[] numbers = input.split(" ");
            int num1;
            int num2;

            for (int i = 0; i < numbers.length; i++) {
                num1 = Integer.parseInt(numbers[i]);
                for (int j = i + 1; j < numbers.length; j++) {
                    num2 = Integer.parseInt(numbers[j]);
                    if (num1 + num2 == expectedSum)
                        return num1*num2;
                }
            }
            return -1;
        }
    }

    public static class Part2 {
        public static int processExpenseReport(String input, Integer expectedSum) {
            String[] numbers = input.split(" ");
            int num1;
            int num2;
            int num3;
            int size = numbers.length;

            for (int i = 0; i < size; i++) {
                num1 = Integer.parseInt(numbers[i]);
                for (int j = i + 1; j < size; j++) {
                    num2 = Integer.parseInt(numbers[j]);
                    if (num1 + num2 < expectedSum)
                        for (int k = j + 1; k < size; k++) {
                            num3 = Integer.parseInt(numbers[k]);
                            if (num1 + num2 + num3 == expectedSum)
                                return num1*num2*num3;
                        }
                }
            }
            return -1;
        }
    }
}
