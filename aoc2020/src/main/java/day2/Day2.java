package day2;

import java.util.Arrays;

public class Day2 {

    public static class Part1 {
        public static int getValidPasswordsCount(String input) {
            int validPasswordsCount = 0;
            for (String line : input.split("\n")) {
                String[] passwordData = line.split(" ");
                int[] occurencesPolicy = Arrays.stream(passwordData[0].split("-")).mapToInt(Integer::parseInt).toArray();
                char policyLetter = passwordData[1].charAt(0);
                int letterCount = (int) passwordData[2].chars().filter(a -> a == policyLetter).count();
                if (letterCount >= occurencesPolicy[0] && letterCount <= occurencesPolicy[1])
                    validPasswordsCount++;
            }
            return validPasswordsCount;
        }
    }

    public static class Part2 {
        public static int getValidPasswordsCount(String input) {
            int validPasswordsCount = 0;
            for (String line : input.split("\n")) {
                String[] passwordData = line.split(" ");
                int[] occurencesPolicy = Arrays.stream(passwordData[0].split("-")).mapToInt(Integer::parseInt).toArray();
                char policyLetter = passwordData[1].charAt(0);
                char letter1 = passwordData[2].charAt(occurencesPolicy[0]-1);
                char letter2 = passwordData[2].charAt(occurencesPolicy[1]-1);
                if (letter1 != letter2 && (letter1 == policyLetter || letter2 == policyLetter))
                    validPasswordsCount++;
            }
            return validPasswordsCount;
        }
    }
}
