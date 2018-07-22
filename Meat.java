/**
 * Calvin Yap
 * #500825267
 * Class Meat is a subclass to GroceryItem and it is a special kind of GroceryItem that overrides the getPrice method 
 * from the super and adjusts it to be multiplied by a weight
 */
public class Meat extends GroceryItem {
	private double weight; // new instance variable
	/**
	 * Class constructor which takes in the same parameters as a GroceryItem in addition to a new variable of weight
	 * which is going to be used to calculate price
	 * @param label name of the GroceryItem
	 * @param price price of the GroceryItem
	 * @param foodCode foodCode of the GroceryItem
	 * @param weight new variable to be used to multiple by price to get a new modified price
	 */
	public Meat(String label, double price, int foodCode, double weight) {
		super(label, price,foodCode); // Call to super to create a GroceryItem
		this.weight = weight; // Sets instance variable to parameter variable
	}
	/**
	 * Return Method that overrides the super of getting the price for the item
	 * since Meat is a specific class we have to factor in the weight of the item
	 * @override getPrice()
	 * @return costPreCalc*weight call from super to get current price and then returns the price by weight of GroceryItem
	 */
	public double getPrice() {
		double costPreCalc = super.getPrice(); // Call to super to getPrice of GroceryItem
		return costPreCalc*weight; // Returns Price*Weight
	}

}
