   /**
    * Calvin Yap
    * #500825267
    * Class to create a GroceryItem, this is the superclass for all Grocery subclasses
    */
public class GroceryItem {
	// Instance Variables
	private String label; // Label of a GroceryItem
	private double price; // Price of a GroceryItem
	private int foodCode; // FoodCode of a GroceryItem
	/**
	 * Empty Class Constructor that creates a blank GroceryItem
	 */
	public GroceryItem() {
		label = "No item"; // Setting to no item to check for errors if an item isn't created properly 
		price = 0.0; // No price
		foodCode = -1; // Negative foodCode to show that it isn't a real item
	}
	/**
	 * Class Constructor that takes in a "food Label, price of the item, and a food code"
	 * @param label is the name of the groceryItem that is added to the Grocery Cart
	 * @param price is the price of the groceryItem that is added to the Grocery Cart
	 * @param foodCode is the foodCode associated to the groceryItem created
	 */
	public GroceryItem(String label,double price,int foodCode) {
		this.label= label; // Sets global variable to parameter 
		this.price = price;// Sets global variable to parameter
		this.foodCode = foodCode;// Sets global variable to parameter
	}
	/**
	 * Return method to get Label of a grocery item
	 * @return instance variable label
	 */
	public String getLabel() {
		return label;	
	}
	/**
	 * Return method to get Price of a grocery item
	 * @return instance variable price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Return method to get food code of a grocery item
	 * @return instance variable foodCode
	 */
	public int getFoodCode() {
		return foodCode;
	}
	/**
	 * Return method to get Label of grocery item
	 * @return instance variable Label
	 */
	public void setLabel(String newLabel) {
		label = newLabel;
	}
	/**
	 * Set method to change the price of grocery item
	 */
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	/**
	 * Set method to change the foodCode of grocery item
	 */
	public void setFoodCode(int newFoodCode) {
		foodCode = newFoodCode;
	}
	
}
