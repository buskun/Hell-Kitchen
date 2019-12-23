package game;

import java.util.Arrays;
import java.util.List;

public class Recipe {
    private List<String> ingredientList;
    private String productName;

    public Recipe(String product, String... ingredients) {
        ingredientList = Arrays.asList(ingredients);
        productName = product;
    }

    public boolean match(List<String> ingredients) {
        return ingredients.containsAll(ingredientList);
    }

    public String getProduct() {
        return productName;
    }
}
