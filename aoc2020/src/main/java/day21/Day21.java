package day21;

import java.util.*;

public class Day21 {

    public static int part1_part2(List<String> input) {
        Set<Food> foods = parseFoods(input);

        // *part1
        // collect all ingredients and allergens
        Set<String> ingredients = new HashSet<>();
        Set<String> allergens = new HashSet<>();
        for (Food food : foods) {
            ingredients.addAll(food.getIngredients());
            allergens.addAll(food.getAllergens());
        }

        // *part2
        // containers for matched ingredients and their allergens
        Map<String, String> matched = new TreeMap<>();
        String matchedAllergen = "";
        String matchedIngredient = "";

        // *part1
        // for each allergen(K) map ingredients(V) that potentially contain those allergens
        // (each allergen is initialized with all ingredients
        // - those will be sieved out in the next step)
        Map<String, Set<String>> possibleMatch = new HashMap<>();
        for (String allergen : allergens)
            possibleMatch.put(allergen, new HashSet<>(ingredients));
        // iterate through each foods allergens
        // and find those ingredients which occur every time
        // when food contains given allergen
        for (Food food : foods)
            for (String allergen : food.getAllergens()) {
                possibleMatch.get(allergen).retainAll(food.getIngredients());
                // *part2
                // collect first matched ingredient and its allergen
                if (possibleMatch.get(allergen).size() == 1) {
                    matchedAllergen = allergen;
                    matchedIngredient = String.join("", possibleMatch.get(matchedAllergen));
                    matched.put(matchedAllergen, matchedIngredient);
                }
            }

        // *part 2
        // match rest of the allergens
        // by removing from their possible ingredients sets
        // previously identified ingredients
        // - starting from the first that we found in previous step
        while (matched.keySet().size() != possibleMatch.keySet().size()) {
            for (String allergen : possibleMatch.keySet()) {
                if (!allergen.equals(matchedAllergen) && possibleMatch.get(allergen).size() > 1)
                    possibleMatch.get(allergen).remove(matchedIngredient);
            }
            for (String allergen : possibleMatch.keySet()) {
                if (possibleMatch.get(allergen).size() == 1 && !matched.containsKey(allergen)) {
                    matchedAllergen = allergen;
                    matchedIngredient = String.join("", possibleMatch.get(matchedAllergen));
                    matched.put(matchedAllergen, matchedIngredient);
                    break;
                }
            }
        }
        System.out.println(String.join(",", matched.values()));

        // *part1
        // now we can look through each foods ingredients
        // and search such ingredients which are not present
        // in any of the mapped allergens sieved out ingredients sets
        int occurences = 0;
        for (Food food : foods) {
            for (String ingredient : food.getIngredients())
                if (possibleMatch
                        .values()
                        .stream()
                        .noneMatch(allergenIngredients -> allergenIngredients.contains(ingredient)))
                    occurences++;
        }
        return occurences;
    }

    private static Set<Food> parseFoods(List<String> input) {
        Set<Food> foods = new HashSet<>();
        for (int i = 0; i < input.size(); i++) {
            Set<String> ingredients = new HashSet<>();
            Set<String> allergens = new HashSet<>();
            List<String> foodContents = Arrays.asList(input.get(i).split(" "));
            for (String ingredient : foodContents) {
                if (ingredient.startsWith("(contains")) {
                    allergens = getAllergens(foodContents.subList(foodContents.indexOf(ingredient) + 1, foodContents.size()));
                    break;
                }
                ingredients.add(ingredient);
            }
            foods.add(new Food(ingredients, allergens));
        }
        return foods;
    }

    private static Set<String> getAllergens(List<String> allergens) {
        Set<String> result = new HashSet<>();
        for (String allergen : allergens) {
            result.add(allergen.substring(0, allergen.length() - 1));
        }
        return result;
    }
}
