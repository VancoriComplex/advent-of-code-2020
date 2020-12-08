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
        private static final Map<String, Set<Bag>> regulations = new HashMap<>();
        private static final Map<Integer, Integer> levelCapacityMap = new HashMap<>();
        private static int totalCapacity = 0;
        private static int level = 0;

        public static int getBagCapacity(List<String> input) {
            readRegulations(input);
            readCapacityRecursive(regulations.get(neededBag), neededBag.getQuantity());
            calculateTotalCapacity();
            return totalCapacity;
        }

        private static void readRegulations(List<String> input) {
            BagReader bagReader = new BagReader();
            for (String regulation : input) {
                regulations.put(bagReader.parseName(regulation), bagReader.parseChildren(regulation));
            }
        }

        private static void readCapacityRecursive(Set<Bag> children, Integer parentsQty) {
            level++;
            if (!levelCapacityMap.containsKey(level))
                levelCapacityMap.put(level, 0);
            if (!children.isEmpty()) {
                children.forEach(child -> {
                    int childQty = child.getQuantity()*parentsQty;
                    readCapacityRecursive(regulations.get(child), childQty);
                    levelCapacityMap.put(level, levelCapacityMap.get(level) + childQty);
                    level--;
                });
            }
        }

        private static void calculateTotalCapacity() {
            levelCapacityMap.values().forEach(levelCapacity -> totalCapacity += levelCapacity);
        }
    }
}
