package scraper.document_parser.chefkoch_parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import scraper.document_parser.DocToStringParser;

public class ChefkochStringParser extends DocToStringParser{
	
	public ChefkochStringParser(Document doc) {
		super(doc);
	}

	@Override
	public String getName() {
		return this.doc.getElementsByClass("page-title").text();
	}

	@Override
	public List<String[]> getIngredients() {
		List<String[]> ingredients = new ArrayList<>();
		Element table = doc.getElementsByClass("incredients").first();
		Elements rows = table.getElementsByTag("tr");
		for (int i = 0; i < rows.size(); i++) {
			Elements cols = rows.get(i).select("td");
			String[] amountIncredient = { cols.get(0).text(), cols.get(1).text() };
			ingredients.add(amountIncredient);
		}
		return ingredients;
	}

	@Override
	public String getPreparationInfo() {
		return doc.getElementById("preparation-info").text();
	}

	@Override
	public String getInstructions() {
		Element instructions = doc.getElementById("rezept-zubereitung");
		String cleanInstructions = Jsoup.clean(instructions.html(),
				"", 
				Whitelist.none(),
				new Document.OutputSettings().prettyPrint(false));
		
	    return cleanInstructions;
	}


}
