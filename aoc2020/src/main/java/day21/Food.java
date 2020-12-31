package day21;

import java.util.Set;

public class Food {
    private final Set<String> ingredients;
    private final Set<String> allergens;

    public Food(Set<String> ingredients, Set<String> allergens) {
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllergens() {
        return allergens;
    }
}
