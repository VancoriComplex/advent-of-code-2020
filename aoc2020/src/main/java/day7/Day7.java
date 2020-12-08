package day7;

import java.util.*;

public class Day7 {

    public static class Part1 {

        private static Bag neededBag = new Bag("shiny gold");

        public static int getValidBagsCount(List<String> input) {
            Map<String, Set<Bag>> regulations = readRegulations(input);
            int validBagsCount = 0;
            for (String parent : regulations.keySet()) {
                if (isValidBag(parent, regulations))
                    validBagsCount++;
            }
            return validBagsCount;
        }

        private static Map<String, Set<Bag>> readRegulations(List<String> input) {
            Map<String, Set<Bag>> regulations = new HashMap<>();
            BagReader bagReader = new BagReader();
            for (String regulation : input) {
                regulations.put(bagReader.parseName(regulation),
                        bagReader.parseChildren(regulation));
            }
            return regulations;
        }

        private static boolean isValidBag(String parent, Map<String, Set<Bag>> regulations) {
            if (regulations.get(parent).contains(neededBag)) {
                return true;
            }
            for (Bag child : regulations.get(parent)) {
                if (isValidBag(child.getName(), regulations))
                    return true;
            }
            return false;
        }
    }

    public static class Part2 {

        private static final Bag neededBag = new Bag("shiny gold", 1);

        public static int getBagCapacity(List<String> input) {
            Map<String, Set<Bag>> regulations = Part1.readRegulations(input);
            return calculateTotalCapacity(neededBag.getName(), regulations) - neededBag.getQuantity();
        }

        private static int calculateTotalCapacity(String parent, Map<String, Set<Bag>> regulations) {
            int result = neededBag.getQuantity();
            result += regulations.get(parent)
                    .stream()
                    .mapToInt(child ->
                            child.getQuantity()*calculateTotalCapacity(child.getName(), regulations))
                    .sum();
            return result;
        }
    }
}
