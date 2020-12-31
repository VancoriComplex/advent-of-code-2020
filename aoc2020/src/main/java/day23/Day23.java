package day23;

import java.util.*;

public class Day23 {

    public static String part1(String input) {
        StringBuilder result = new StringBuilder(input);
        String currentCup = String.valueOf(result.charAt(0));
        String threeCups;
        int destination;
        for (int i = 0; i < 100; i++) {
            threeCups = pickUpCups(result, currentCup, 3);
            destination = getDestination(result, currentCup);
            insertBackThreeCups(result, destination, threeCups);
            currentCup = getCurrentCup(result, currentCup);
        }
        return result.toString();
    }

    public static long part2(String input) {
        //we need to know only one thing about each cup besides its value
        Map<Integer, Integer> seen = parseInput(input);
        int parent = 1;
        for (int i = 0; i < 10000000; i++) {
            int child1 = getChild(seen, parent);
            int child2 = getChild(seen, child1);
            int child3 = getChild(seen, child2);
            int newParent = parent - 1;
            while (isEqualTo(child1, child2, child3, newParent)) {
                newParent -= 1;
            }
            if (newParent < 1)
                newParent = 1000000;
            int newParentChild = getChild(seen, newParent);
            seen.put(parent, getChild(seen, child3));
            seen.put(child3, newParentChild);
            seen.put(newParent, child1);
            parent = getChild(seen, parent);
        }
        int neededChild1 = seen.get(1);
        int neededChild2 = seen.get(neededChild1);
        return (long) neededChild1*neededChild2;
    }

    private static boolean isEqualTo(int child1, int child2, int child3, int newParent) {
        return newParent == child1
                || newParent == child2
                || newParent == child3;
    }

    private static int getChild(Map<Integer, Integer> seen, int parent) {
        return seen.getOrDefault(parent, parent + 1);
    }

    private static Map<Integer, Integer> parseInput(String input) {
        Map<Integer, Integer> seen = new LinkedHashMap<>();
        String[] split = input.split("");
        int size = split.length;
        int[] digits = new int[size + 1];
        for (int i = 0; i < size; i++) {
            int nextDigit = Integer.parseInt(split[i]);
            digits[i] = nextDigit;
        }
        digits[size] = 10;

        for (int i = 0; i < size; i++) {
            seen.put(digits[i], digits[i + 1]);
        }
        seen.put(1000000, digits[0]);
        return seen;
    }

    private static String getCurrentCup(StringBuilder result, String currentCup) {
        currentCup = String.valueOf(result.charAt((result.indexOf(currentCup) + 1)%result.length()));
        return currentCup;
    }

    private static String pickUpCups(StringBuilder result, String currentCup, int qty) {
        int currentCupIndex = result.indexOf(currentCup);
        String pickUp = "";
        for (int i = 0; i < qty; i++) {
            int pickIndex = (currentCupIndex + 1)%result.length();
            if (pickIndex < currentCupIndex) {
                pickUp = pickUp.concat(String.valueOf(result.charAt(0)));
                result.deleteCharAt(0);
            } else {
                pickUp = pickUp.concat(String.valueOf(result.charAt(pickIndex)));
                result.deleteCharAt(pickIndex);
            }
        }
        return pickUp;
    }

    private static int getDestination(StringBuilder result, String currentCup) {
        int destination;
        destination = Integer.parseInt(currentCup) - 1;
        while (!result.toString().contains(String.valueOf(destination))) {
            destination -= 1;
            if (destination < 0) {
                destination = Arrays.stream(result.toString().split("")).mapToInt(Integer::parseInt).max().getAsInt();
            }
        }
        return destination;
    }

    private static void insertBackThreeCups(StringBuilder result, int destination, String threeCups) {
        int destinationIndex = result.indexOf(String.valueOf(destination));
        result.insert(destinationIndex + 1, threeCups);
    }
}
