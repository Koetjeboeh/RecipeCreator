package xyz.controlepaneel.recipe.recipecreator;

public class RecipeHelper{
    private String RecipeName;
    private String RecipeText;

    public RecipeHelper(String rName, String rText) {
        RecipeName = rName;
        RecipeText = rText;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public String getRecipeText() {
        return RecipeText;
    }

    public void setRecipeText(String recipeText) {
        RecipeText = recipeText;
    }
}