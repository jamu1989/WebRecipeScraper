package scraper;
import org.jsoup.nodes.Document;

import scraper.connection.ConnectionThread;
import scraper.document_parser.chefkoch_parser.ChefkochStringParser;
import scraper.document_parser.kochbar_parser.KochbarStringParser;
import scraper.recipe_builder.Recipe;
import scraper.recipe_builder.RecipeBuilder;

public class Example {
	public static void main(String[] args) throws InterruptedException {
		getChefKochRecipe("https://www.chefkoch.de/rezepte/1732971282320962/Apfelmus-Vanillepudding-Kuchen.html");
		Thread.sleep(2000);
		System.out.println("\n\n\n\n\n\n\n\n");
		getKochbarRecipe("https://www.kochbar.de/rezept/409352/Wuerziges-Wildschweinbrot-mit-Rosenkohl.html");
	}
	
	public static void getKochbarRecipe(String url) {
		new ConnectionThread(url) {
			@Override
			public void onReturn(Document doc) {
				RecipeBuilder rb = new RecipeBuilder(new KochbarStringParser(doc));
				rb.buildRecipe();
				showRecipe(rb.getRecipe());
			}
		};
	}
	
	public static void getChefKochRecipe(String url) {
		new ConnectionThread(url) {
			@Override
			public void onReturn(Document doc) {
				RecipeBuilder rb = new RecipeBuilder(new ChefkochStringParser(doc));
				rb.buildRecipe();
				showRecipe(rb.getRecipe());
			}
		};
	}
	
	public static void showRecipe(Recipe recipe) {
		System.out.println(recipe.toString());
	}
	
}
