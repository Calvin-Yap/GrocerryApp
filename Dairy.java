/**
 * Calvin Yap
 * #500825267
 * Class Diary is a subclass to GroceryItem and it is a special kind of GroceryItem that overrides the getPrice method 
 * from the super and adjusts it to be multiplied by a volume
 */
public class Dairy extends GroceryItem{

	private double volume;
	/**
	 * Class constructor which takes in the same parameters as a GroceryItem in addition to a new variable of voluume
	 * which is going to be used to calculate price
	 * @param label name of the GroceryItem
	 * @param price price of the GroceryItem
	 * @param foodCode foodCode of the GroceryItem
	 * @param volume new variable to be used to multiple by price to get a new modified price
	 */
	public Dairy (String label, double price, int foodCode, double volume) {
		super(label, price,foodCode);
		this.volume= volume;
	}
	/**
	 * Return Method that overrides the super of getting the price for the item
	 * since Diary is a specific class we have to factor in the volume of the item
	 * @override getPrice()
	 * @return costPreCalc*volume call from super to get current price and then returns the price by weight of GroceryItem
	 */
	public double getPrice() {
		double costPreCalc = super.getPrice(); // Call to super to getPrice of GroceryItem
		return costPreCalc*volume; // Returns price*volume
	}
	
}
