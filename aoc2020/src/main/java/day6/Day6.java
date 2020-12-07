package day6;

import java.util.*;

public class Day6 {

    public static class Part1 {

        public static int getAnswersCount(List<String> customDeclarationAnswers) {
            int totalCount = 0;
            String groupAnswers = "";
            String lastAnswer = customDeclarationAnswers.get(customDeclarationAnswers.size() - 1);
            for (String answer : customDeclarationAnswers) {
                groupAnswers = groupAnswers.concat(answer);
                if (answer.isEmpty() || answer.equals(lastAnswer)) {
                    totalCount += groupAnswers.chars().distinct().count();
                    groupAnswers = "";
                }
            }
            return totalCount;
        }
    }

    public static class Part2 {

        public static int getAnswersCount(List<String> customDeclarationAnswers) {
            int totalYesAnswerCount = 0;
            List<Set<String>> singleGroupAnswers = new ArrayList<>();
            String lastAnswer = customDeclarationAnswers.get(customDeclarationAnswers.size() - 1);
            for (String answer : customDeclarationAnswers) {
                if (!answer.isEmpty()) {
                    singleGroupAnswers.add(new HashSet<>(Arrays.asList(answer.split(""))));
                }
                if (answer.isEmpty() || answer.equals(lastAnswer)) {
                    Set<String> intersection = singleGroupAnswers.get(0);
                    for (int i = 1; i < singleGroupAnswers.size(); i++) {
                        intersection.retainAll(singleGroupAnswers.get(i));
                    }
                    totalYesAnswerCount += intersection.size();
                    singleGroupAnswers.clear();
                }
            }
            return totalYesAnswerCount;
        }
    }
}
