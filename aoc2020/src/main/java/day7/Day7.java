package day7;

import java.util.*;

public class Day7 {

    public static class Part1 {

        private static final Bag neededBag = new Bag("shiny gold");
        private static final Map<Bag, Set<Bag>> regulations = new HashMap<>();
        private static int validBagsCount = 0;
        private static boolean bagFound;

        public static int getValidBagsCount(List<String> input) {
            readRegulations(input);
            for (Bag bag : regulations.keySet()) {
                bagFound = false;
                countValidBagsRecursive(regulations.get(bag));
            }
            return validBagsCount;
        }

        private static void readRegulations(List<String> input) {
            BagReader bagReader = new BagReader();
            for (String regulation : input) {
                Bag newBag = bagReader.parseBag(regulation);
                regulations.put(newBag, newBag.getChildren());
            }
        }

        private static void countValidBagsRecursive(Set<Bag> bags) {
            if (!bags.isEmpty() && !bagFound) {
                if (bags.contains(neededBag)) {
                    bagFound = true;
                    validBagsCount++;
                } else {
                    bags.forEach(bag -> countValidBagsRecursive(regulations.get(bag)));
                }
            }
        }
    }

    public static class Part2 {

        private static final Bag neededBag = new Bag("shiny gold", 1);
        private static final Map<Bag, Set<Bag>> regulations = new HashMap<>();
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
                Bag newBag = bagReader.parseBag(regulation);
                regulations.put(newBag, newBag.getChildren());
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
