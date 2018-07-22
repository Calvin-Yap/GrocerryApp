import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Calvin Yap
 * #500825267
 * This Class is a subclass to FoodType creates an arrayList of FoodType objects that is later used in a NutritionScanner 
 * in order to get the nutrition facts from each GroceryItem
 */
public class NutritionChart extends FoodType{
	private Map<Integer,FoodType> foodList = new TreeMap<Integer,FoodType>();
	Set<Integer> keyFoodCodeSet = foodList.keySet();
	/**
	 * Constructor Method that basically did what a fill() method would do and it adds FoodTypes into the araylist above
	 * uses the addItem(FoodType item); to add to the list
	 * @throws FileNotFoundException, InputMismatchExceptionIOException, 
	 */
	public NutritionChart() throws FileNotFoundException, InputMismatchException{
		Scanner scanner = new Scanner (new File("nutrition.txt"));
		while(scanner.hasNext()) {
			Integer keyFoodCode = scanner.nextInt();
			String foodLabel = scanner.next();
			Integer measure = scanner.nextInt();
			Integer calories = scanner.nextInt();
			Integer sugar = scanner.nextInt();
			Integer fat = scanner.nextInt();
			Integer carbs = scanner.nextInt();
			FoodType newFoodType = new FoodType(keyFoodCode,foodLabel,measure, calories, sugar, fat, carbs);
			foodList.put(keyFoodCode, newFoodType);
		}
		scanner.close();
	}
	/**
	 * Method uses the parameter FoodType item and adds it to the ArrayList created in this class
	 * @param item is a FoodType that will be added to the list of GroceryItems
	 */
	public void addItem(FoodType item) {
	}
	/**
	 * Return Method that takes in an integer foodCode and runs through the arrayList and sees if in that array an element has the same foodCode as the parameter
	 * returns found which is the element in the list 
	 * @param foodCode is the code that is used to search for the wanted element
	 * @return found which is an empty FoodType but once going through the if statement will return the FoodType that has the same foodCode
	 */
	public FoodType getFoodType (int foodCode) {

		return foodList.get(foodCode);
	}

}
