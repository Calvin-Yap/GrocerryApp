import java.util.Comparator;
/**
 * Calvin Yap
 * #500825267
 * This class uses the interface Comparator to compare FoodTypes
 * Is used in the nutritionScanner panel to sort the items by Fat
 * will be used in button listener to sort
 */
public class FoodTypeFatComparator implements Comparator <FoodType> {
	/**
	 * Implemented method from Comparator class
	 * will sort the list from Greatest to Lowest
	 * @param first this is the first item that you are putting in to compare
	 * @param first1 this is the second item that you are putting in to compare
	 * @return gets the Fat for their respective FoodTypes and subtracts the second one by first 
	 * which will give the greatest to least
	 */
	public int compare(FoodType first, FoodType first1) {
		return (first1.getFat()*first1.getMeasure()) - (first.getFat()*first.getMeasure()) ;
	}


}
