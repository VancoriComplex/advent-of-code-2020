package day21;

import java.util.*;

public class Day21 {

    public static int part1(List<String> input) {
        Set<Food> foods = parseFoods(input);

        // *part1
        // collect all ingredients and allergens
        Set<String> ingredients = new HashSet<>();
        Set<String> allergens = new HashSet<>();
        for (Food food : foods) {
            ingredients.addAll(food.getIngredients());
            allergens.addAll(food.getAllergens());
        }

        // *part1
        // for each allergen(K) map its ingredients(V)
        // (each allergen is initialized with all ingredients
        // - those will be sieved out in the next step)
        Map<String, Set<String>> allergenMap = new HashMap<>();
        for (String allergen : allergens)
            allergenMap.put(allergen, new HashSet<>(ingredients));
        // iterate through each foods allergens
        // and sieve out ingredients that occur every time
        // when food contains given allergen
        for (Food food : foods)
            for (String allergen : food.getAllergens())
                allergenMap.get(allergen).retainAll(food.getIngredients());

        // *part1
        // now we can look through each foods ingredients
        // and search such ingredients which are not present
        // in any of the mapped allergens sieved out ingredients sets
        int occurences = 0;
        for (Food food : foods) {
            for (String ingredient : food.getIngredients())
                if (allergenMap
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
