package taskapi;

import java.util.ArrayList;
import java.util.List;

public class OrderCreate {

    private List<String> ingredients; // Список id ингридиенты для бургера

    public OrderCreate(List<Ingredient> ingridients) {
        ingredients = new ArrayList<>();
        for (Ingredient elem : ingridients) {
            this.ingredients.add(elem.id);
        }

    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredientIds) {
        this.ingredients = ingredientIds;
    }

}
