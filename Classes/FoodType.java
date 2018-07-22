/**
 * Calvin Yap
 * #500825267
 * Superclass for every food item in the NutritionScanner 
 * creates a base FoodType and implements interface Comparable for future scanner usage
 */
public class FoodType implements Comparable <FoodType> {
	// Instance Variables
	private String Label; // String to store name of FoodType 
	private int foodCode, calories, sugar, fat, carbs; // Nutrition Facts of FoodType
	private int measure = 1; // Default set to 1, is used to count how many times a FoodType is scanned 
	/**
	 * Empty Constructor 
	 * used for debugging, if item from GroceryCart is not intergrated to a FoodType then will return a "No Item"S
	 */
	public FoodType() {
		Label = "No Item"; // No item label
		foodCode = -1; // -1 to show doesn't exist
		calories = 0; // 0 
		sugar =0; //0
		fat =0; //0
		carbs =0; // 0
	}
	/**
	 * Class Constructor that takes in a "label", the same FoodCode as GroceryItem, calories of the item, sugar of the item, fat of the item, and the time it occurs in the list being measure
	 * @param Label is the name of the FoodType, sets instance variable to parameter
	 * @param foodCode is the foodCode in the FoodType, sets instance variable to parameter, has to be the same foodCode in GroceryCart
	 * @param calories is the calories in the FoodType, sets instance variable to parameter
	 * @param sugar is the sugar in of the FoodType, sets instance variable to parameter
	 * @param fat is the fat in of the FoodType, sets instance variable to parameter
	 * @param carbs is the carbs in of the FoodType, sets instance variable to parameter
	 * @param measure is the measure in of the FoodType, sets instance variable to parameter, starts at 1
	 */
	public FoodType(int foodCode, String label, int measure, int calories, int sugar, int fat,int carbs) {
	this.Label = label; // Sets global variable to parameter 
	this.foodCode = foodCode;// Sets global variable to parameter 
	this.calories = calories;// Sets global variable to parameter 
	this.sugar = sugar;// Sets global variable to parameter 
	this.fat = fat;// Sets global variable to parameter 
	this.carbs = carbs;// Sets global variable to parameter 
	this.measure = measure;// Sets global variable to parameter 
	}
	/**
	 * Implemented method for Compare class
	 * @param other another FoodType object, it is used to compare to the current one
	 */
	public int compareTo(FoodType other) {
		 if (getMeasure()*calories > other.calories*other.getMeasure()) { 
			 return -1;
			 }
		 if (getMeasure()*calories <other.calories*other.getMeasure()) {
			 return 1;
			 }
		    return 0;
	}
	/**
	 * Return Method that is used to format how the nutrition facts are displayed to the panel
	 * same as how it is displayed in the video posted
	 * @return  (Label + ": Cals " + measure*calories + " Sugar "+measure*sugar +" Fat " + measure*fat + " Carbs "+ measure*carbs); 
	 * every value is multiplied by measure for when the same item is scanned twice by the nutrition scanner
	 */
	public String display(){
		return (Label + ": Cals " + measure*calories + " Sugar "+measure*sugar +" Fat " + measure*fat + " Carbs "+ measure*carbs);
		
	}
	/**
	 * Return method to get foodCode of a FoodType
	 * @return instance variable for foodCode
	 */
	public int getFoodCode() {
		return foodCode;
	}
	/**
	 * Return method to get calories of a FoodType
	 * @return instance variable for calories
	 */
	public int getCalories() {
		return calories;
	}
	/**
	 * Return method to get sugar of a FoodType
	 * @return instance variable for sugar
	 */
	public int getSugar() {
		return sugar;
	}
	/**
	 * Return method to get fat of a FoodType
	 * @return instance variable for fat
	 */
	public int getFat() {
		return fat;
	}
	/**
	 * Return method to get carbs of a FoodType
	 * @return instance variable for carbs
	 */
	public int getCarbs() {
		return carbs;
	}
	/**
	 * Method which takes in a parameter and is used to keep count how many of the same items are scanned
	 * So it is used whenever there is another item with the same foodCode
	 * adds how many more to the measure instance variable
	 * @param adder the number that you are going to increment measure by
	 */
	public void addFoodCount(int adder) {
		measure+= adder; // adds extra adder to measure
	}
	/**
	 * Method which resets the instance variable measure
	 */
	public void resetMeasure() {
		measure = 1; // sets it back to the default 1
	}
	/**
	 * Return Method that gives back the measure instance variable
	 * Will be used for the sorting buttons
	 */
	public int getMeasure() {
		return measure;
	}

}
