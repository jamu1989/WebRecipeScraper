package scraper.recipe_builder;

public class Ingredient implements Comparable<Ingredient>{
	private String amount;
	private String name;

	public Ingredient(String amount, String name) {
		this.amount = amount;
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Ingredient other) {
		Ingredient o = (Ingredient)other;
		return this.name.compareTo(o.getName());
	}
	
	public String toString() {
		//.out.println(String.format("%30s %25s %10s %25s %10s %20s %20s", "Item", "|", "Price($)", "|", "Qty", "|", "ID"));
		return String.format("%-50s %10s ", this.name,this.amount);
	}

}
