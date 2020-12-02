package day2;

import java.util.Arrays;
import java.util.List;

public class Day2 {

    public static class Part1 {
        public static int getValidPasswordsCount(List<String> input) {
            int validPasswordsCount = 0;
            for (String line : input) {
                String[] passwordData = getPasswordData(line);
                int[] policyRules = getPolicyRules(passwordData[0]);
                char policyLetter = getPolicyLetter(passwordData[1]);
                String password = passwordData[2];
                if (passwordMatchPolicyRules(password, policyLetter, policyRules))
                    validPasswordsCount++;
            }
            return validPasswordsCount;
        }

        private static String[] getPasswordData(String line) {
            return line.split(" ");
        }

        private static int[] getPolicyRules(String policyRule) {
            return Arrays.stream(policyRule.split("-")).mapToInt(Integer::parseInt).toArray();
        }

        private static char getPolicyLetter(String passwordDatum) {
            return passwordDatum.charAt(0);
        }

        private static boolean passwordMatchPolicyRules(String password, char policyLetter, int[] policyRules) {
            int letterCount = getLetterCount(password, policyLetter);
            return letterCount >= policyRules[0] && letterCount <= policyRules[1];
        }

        private static int getLetterCount(String password, char policyLetter) {
            return (int) password.chars().filter(a -> a == policyLetter).count();
        }
    }

    public static class Part2 {
        public static int getValidPasswordsCount(List<String> input) {
            int validPasswordsCount = 0;
            for (String line : input) {
                String[] passwordData = line.split(" ");
                int[] policyRules = Arrays.stream(passwordData[0].split("-")).mapToInt(Integer::parseInt).toArray();
                char policyLetter = passwordData[1].charAt(0);
                char letter1 = passwordData[2].charAt(policyRules[0]-1);
                char letter2 = passwordData[2].charAt(policyRules[1]-1);
                if (letter1 != letter2 && (letter1 == policyLetter || letter2 == policyLetter))
                    validPasswordsCount++;
            }
            return validPasswordsCount;
        }
    }
}
