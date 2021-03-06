package day7;

import java.util.*;

class BagReader {

    public Map.Entry<String, Set<Bag>> parseBag(String input) {
        return newBagEntry(parseName(input), parseChildren(input));
    }

    private <K,V>Map.Entry<K,V> newBagEntry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private String parseName(String input) {
        return input.split(" bags contain ")[0];
    }

    private Set<Bag> parseChildren(String input) {
        Set<Bag> result = new HashSet<>();
        String[] childrenToParse = input.split(" bags contain ")[1].split("\\s+bag([s]?)+[,.]+(\\s)?");
        if (childrenToParse[0].equals("no other"))
            return result;
        for (String child : childrenToParse) {
            result.add(parseChild(child));
        }
        return result;
    }

    private Bag parseChild(String input) {
        String name = input.replaceAll("^[0-9]+\\s", "");
        Integer qty = parseChildQty(input);
        return new Bag(name, qty);
    }

    private Integer parseChildQty(String input) {
        int qty = Integer.parseInt(input.replaceAll("[a-z]*", "").trim());
        return qty;
    }
}
