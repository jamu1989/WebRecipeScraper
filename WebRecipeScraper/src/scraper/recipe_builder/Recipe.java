package scraper.recipe_builder;

import java.util.List;

public class Recipe {
	private String name;
	private String cookingInstructions;
	private String preparationInfo;
	private List<Ingredient> ingredients;

	public Recipe(String name, String cookingInstructions, String preparationInfo, List<Ingredient> ingredients) {
		this.name = name;
		this.cookingInstructions = cookingInstructions;
		this.preparationInfo = preparationInfo;
		this.ingredients = ingredients;
	}
	
	public String toString() {
		StringBuilder recipeString = new StringBuilder();
		recipeString.append(String.format("Rezept: %s\n\n",this.name));
		recipeString.append(String.format("Info: %s\n",this.preparationInfo));
		recipeString.append("\nZutaten:\n");
		for(Ingredient i:this.ingredients) {
			recipeString.append(i.toString()+"\n");
		}
		recipeString.append(String.format("\nZubereitung:\n %s",this.cookingInstructions));
		return recipeString.toString();
	}
}
