package scraper.document_parser;

import java.util.List;

import org.jsoup.nodes.Document;

/**
 * A Document Parser should parse a JSOUP {@link Document} to Strings.<br>
 * The Document Parser must be overwritten for each new HTML source.
 * <p>
 * The document parser is required for the RecipeBuilder
 * 
 * @author Jan Mueller
 */
public abstract class DocToStringParser {
	protected Document doc;
	public DocToStringParser(Document doc) {
		this.doc = doc;
	}
	
	/**
	 * This method returns the recipe name 
	 * @return String 
	 */
	public abstract String getName() ;
	
	/**
	 * This method returns the incredients List.<br>
	 * The list should follow a specific structure:<br>
	 * 
	 * <ul> 
	 * <b>Incredient List</b>
	 * <li> Amount,Name
	 * <li> Amount,Name
	 * <li> Amount,Name
	 * </ul>
	 * @return List&lt;String[ ]&gt;
	 */
	public abstract List<String[]> getIngredients() ;

	/**
	 * This method returns the preperation info.<br>
	 * A preperation info is something like: <br>
	 * working time<br>
	 * required skill level<br>
	 * ... 
	 * @return String 
	 */
	public abstract String getPreparationInfo() ;

	/**
	 * This method returns the cooking instructions.
	 * 
	 * @return String 
	 */
	public abstract String getInstructions() ;
}
