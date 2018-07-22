import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JTextArea;
/**
 * Calvin Yap
 * #500825267
 * Class to create a NutritionScanner, subclass to the super FoodType
 * Will look at the current NutritionChar in comparison to an empty list of FoodTypes
 * If food type is already in the empty list than instead of adding another instance of it, it will increment measure in FoodType
 * Used to display into a JTextArea
 */
public class NutritionScanner extends FoodType {
	private ArrayList <FoodType> items = new ArrayList <FoodType>(); // ArrayList of an empty items list
	
	/**
	 * Empty Constructor, when this is created a new NutritionChart is created
	 */
	public NutritionScanner() {
		try {
			NutritionChart chart = new NutritionChart();
		} catch (InputMismatchException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that looks through the current FoodType objects in the items arayList
	 * if there is an item on this list with the same foodCode, the measure variable is increased instead of being added again
	 * if no matching code then the object is added into the items array
	 * @param foodCode is what the method is using to search fod
	 */
	public void scanFoodItem(int foodCode) {
		try {
			NutritionChart newChart = new NutritionChart();
			FoodType temp = new FoodType();
			boolean isIn = false;
			for (int i = 0; i<items.size(); i++) {
				if (foodCode == items.get(i).getFoodCode()){
					items.get(i).addFoodCount(1);
					isIn = true;
				}
			}
			if (isIn == false) {
				temp = newChart.getFoodType(foodCode);
				items.add(temp);	
			}
				else 
					isIn  = false;
			} catch (InputMismatchException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	/**
	 * Method to display created items list to a JTextArea
	 * @param displayArea is the specified JTextArea which the list is going be displayed on
	 */
	public void displayAll(JTextArea displayArea) {
		for(int i = 0; i < items.size(); i++){
            displayArea.append((items.get(i).display()) + "\n");
		}
	}
	/**
	 * Method that clears the screen area of any previously displayed FoodTypes
	 * @param area is a specified JTextArea that will display this groceryCart
	 */
	public void ScreenClear(JTextArea area) {
		area.setText(null);
	}
	/**
	 * Method that clears the list of FoodTypes
	 */
	public void clear() {
		items.clear();
	}
	/**
	 * Method that resets the measure variable for after the clear button is pressed
	 * every FoodType object's measure is set back to 1
	 */
	public void resetMeasure() {
		for(int i = 0; i < items.size();i++) {
			items.get(i).resetMeasure(); 
		}
	}
	/**
	 * Return Method that gives back the arrayList created in this class
	 * @return items which is the list of FoodTypes
	 */
	public ArrayList<FoodType> getList() {
		return items;
	}
}


