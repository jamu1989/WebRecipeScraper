package scraper.document_parser.kochbar_parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import scraper.document_parser.DocToStringParser;

public class KochbarStringParser extends DocToStringParser {

	public KochbarStringParser(Document doc) {
		super(doc);
	}

	@Override
	public String getName() {
		return this.doc.getElementsByClass("kb-content-h1-wrapper").first().select("h1").text();

	}

	@Override
	public List<String[]> getIncredients() {
		List<String[]> incredients = new ArrayList<>();
		Element table = doc.getElementsByClass("kb-recipe-ingredient-table").first();
		Elements rows = table.getElementsByTag("tr");
		for (int i = 0; i < rows.size(); i++) {
			Elements cols = rows.get(i).select("td");
			String[] amountIncredient = { cols.get(0).text(), cols.get(1).text() };
			incredients.add(amountIncredient);
		}
		return incredients;
	}

	@Override
	public String getPreparationInfo() {
		StringBuilder prepInfo = new StringBuilder();
		Element table = doc.getElementsByClass("kb-width-100 rtli-bgcolor-14 rtli-pl-small rtli-pr-small rtli-pb-small")
				.first().select("table").get(1);
		Elements rows = table.getElementsByTag("tr");
		for (int i = 0; i < rows.size(); i++) {
			Elements cols = rows.get(i).select("td");
			if(cols.size() == 2)
				prepInfo.append(String.format("%s %s\n", cols.get(0).text(), cols.get(1).text()));
			else
				prepInfo.append(String.format("%s \n", cols.get(0).text()));
		}
		return prepInfo.toString();

	}

	@Override
	public String getInstructions() {
		Element instructions = doc.getElementsByClass("kb-steps-wrapper").first();
		String instructionsString = Jsoup.clean(instructions.html(),
				"", 
				Whitelist.none(),
				new Document.OutputSettings().prettyPrint(false));
		List<String> list = new ArrayList<String>(Arrays.asList(instructionsString.split("\n")));
		List<String> cleanList = new ArrayList<>();
		list.forEach(string -> cleanList.add(string.replaceAll("\\s+", " ")));
		cleanList.removeAll(Arrays.asList(" ",""));
		return String.join("\n", cleanList);
	}
	
	
}
