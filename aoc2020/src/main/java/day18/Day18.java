package day18;

import java.util.Arrays;
import java.util.List;

public class Day18 {

    public static long part1(List<String> input) {
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] expression = input.get(i).split(" ");
            sum += evaluateV1(expression);
        }
        return sum;
    }

    public static long part2(List<String> input) {
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] expression = input.get(i).split(" ");
            sum += evaluateV2(expression);
        }
        return sum;
    }

    private static long evaluateV1(String[] expression) {
        long result = 0;
        boolean addition = false;
        boolean multiplication = false;

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].startsWith("(")) {
                String[] nestedExpression = getNestedExpression(expression, i);
                if (addition) {
                    result += evaluateV1(nestedExpression);
                    addition = false;
                } else if (multiplication) {
                    result *= evaluateV1(nestedExpression);
                    multiplication = false;
                } else {
                    result += evaluateV1(nestedExpression);
                }
                int nestCount = getNestCount(expression[i], "(");
                i = skipNestedExpression(expression, i, nestCount);
                continue;
            }
            if (addition) {
                result += Integer.parseInt(expression[i]);
                addition = false;
                continue;
            }
            if (multiplication) {
                result *= Integer.parseInt(expression[i]);
                multiplication = false;
                continue;
            }
            if (expression[i].equals("+")) {
                addition = true;
                continue;
            }
            if (expression[i].equals("*")) {
                multiplication = true;
                continue;
            }
            result += Integer.parseInt(expression[i]);
        }
        return result;
    }

    private static long evaluateV2(String[] expression) {
        long result = 0;
        boolean addition = false;
        boolean multiplication = false;

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].startsWith("(")) {
                String[] nestedExpression = getNestedExpression(expression, i);
                if (addition) {
                    result += evaluateV2(nestedExpression);
                    addition = false;
                } else if (multiplication) {
                    long valueToMultiply;
                    String[] additionExpression = getNextAdditionExpression(expression, i);
                    valueToMultiply = evaluateV2(additionExpression);
                    i += additionExpression.length - 1;
                    result *= valueToMultiply;
                    multiplication = false;
                    continue;
                } else {
                    result += evaluateV2(nestedExpression);
                }
                int nestCount = getNestCount(expression[i], "(");
                i = skipNestedExpression(expression, i, nestCount);
                continue;
            }
            if (addition) {
                result += Integer.parseInt(expression[i]);
                addition = false;
                continue;
            }
            if (multiplication) {
                long valueToMultiply;
                String[] additionExpression = getNextAdditionExpression(expression, i);
                valueToMultiply = evaluateV2(additionExpression);
                i += additionExpression.length - 1;
                result *= valueToMultiply;
                multiplication = false;
                continue;
            }
            if (expression[i].equals("+")) {
                addition = true;
                continue;
            }
            if (expression[i].equals("*")) {
                multiplication = true;
                continue;
            }
            result += Integer.parseInt(expression[i]);
        }
        return result;
    }

    private static String[] getNestedExpression(String[] expression, int startIndex) {
        int nestCount = 0;
        int endIndex = 0;
        for (int i = startIndex; i < expression.length; i++) {
            if (expression[i].startsWith("(")) {
                nestCount += getNestCount(expression[i], "(");
            }
            if (expression[i].endsWith(")")) {
                nestCount -= getNestCount(expression[i], ")");
            }
            if (nestCount == 0) {
                endIndex = i;
                break;
            }
        }
        String[] innerExpression = new String[endIndex - startIndex + 1];
        for (int i = startIndex, j = 0;
             i <= endIndex && j < innerExpression.length;
             i++, j++) {
            if (i == startIndex)
                innerExpression[j] = expression[i].substring(expression[i].indexOf("(") + 1);
            else if (i == endIndex)
                innerExpression[j] = expression[i].substring(0, expression[i].lastIndexOf(")"));
            else
                innerExpression[j] = expression[i];
        }
        return innerExpression;
    }

    private static int skipNestedExpression(String[] expression, int index, int nestCount) {
        int count = nestCount;
        for (int i = index; i < expression.length; i++) {
            if (expression[i].endsWith(")"))
                count -= getNestCount(expression[i], ")");
            if (i != index && expression[i].startsWith("("))
                count += getNestCount(expression[i], "(");
            if (count <= 0)
                return i;
        }
        return -1;
    }

    private static String[] getNextAdditionExpression(String[] expression, int startIndex) {
        int endIndex = 0;
        int nestCount = 0;
        for (int i = startIndex; i < expression.length; i++) {
            if (expression[i].startsWith("("))
                nestCount += getNestCount(expression[i], "(");
            if (expression[i].endsWith(")"))
                nestCount -= getNestCount(expression[i], ")");
            if (expression[i].equals("*") && nestCount == 0) {
                endIndex = i - 1;
                break;
            }
            if (i == expression.length - 1)
                endIndex = i;
        }
        String[] additionExpression = new String[endIndex - startIndex + 1];
        for (int i = startIndex, j = 0;
             i <= endIndex && j < additionExpression.length;
             i++, j++)
        {
            additionExpression[j] = expression[i];
        }
        return additionExpression;
    }

    private static int getNestCount(String nestedValue, String parentheses) {
        return (int) Arrays.stream(nestedValue.split(""))
                .filter(x -> x.equals(parentheses))
                .count();
    }
}
