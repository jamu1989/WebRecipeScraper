package scraper.recipe_builder;

import java.util.ArrayList;
import java.util.List;

import scraper.document_parser.DocToStringParser;

public class RecipeBuilder {
	private Recipe recipe;
	private DocToStringParser parser;
	public RecipeBuilder(DocToStringParser parser) {
		this.parser = parser;
		this.recipe = null;
	}
	
	public void buildRecipe() {
		String name;
		String cookingInstructions;
		String preparationInfo;
		List<Ingredient> ingredients = new ArrayList<>();
		
		name = this.parser.getName();
		cookingInstructions = this.parser.getInstructions();
		preparationInfo = this.parser.getPreparationInfo();
		for(String[] amountIngredientPair : this.parser.getIngredients()) {
			ingredients.add(new Ingredient(amountIngredientPair[0],amountIngredientPair[1]));
		}
		this.recipe = new Recipe(name, cookingInstructions, preparationInfo, ingredients);	
		}
	
	public Recipe getRecipe() {
		return this.recipe;
	}
}
