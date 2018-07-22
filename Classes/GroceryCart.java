import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.JTextArea;
/**
 * Calvin Yap
 * #500825267
 * Class to create a GroceryCart, this is a subclass to GroceryItem and creates a GroceryCart that is filled with GroceryItems
 * this class will create a GroceryCart that will be displayed on a JTextArea 
 */
public class GroceryCart extends GroceryItem {
	
	private Stack<GroceryItem> shoppingList = new Stack<GroceryItem>();
    private ListIterator<GroceryItem> shoppingIter = shoppingList.listIterator(shoppingList.size());


	/**
	 * Method fill() creates 10 groceryItems with labels, prices, and foodcodes
	 * uses other method addItem(GroceryItem) to add it to the list
	 * @throws FileNotFoundException 
	 */
	public void fill() throws FileNotFoundException, InputMismatchException, IOException {
		Scanner scanner =new Scanner(new File("groceryItems.txt"));
		String item,specialCase = "";
		int foodCode, weight, volume = 0;
		double price = 0.0;
		
		while(scanner.hasNext()) {
			foodCode  = scanner.nextInt();
			item = scanner.next();
				if (item.equals("Meat"))
				{
					specialCase ="";
					while (!scanner.hasNextDouble()) {
					specialCase +=scanner.next() + " ";
					}
					price = scanner.nextDouble();
					weight = scanner.nextInt();
					GroceryItem newMeat = new Meat(specialCase,price, foodCode,weight);
					shoppingList.push(newMeat);
				}
				else if (item.equals("Dairy")) {
					specialCase ="";
					while (!scanner.hasNextDouble()) {
						specialCase +=scanner.next() + " ";
					}
					price = scanner.nextDouble();
					volume = scanner.nextInt();
					GroceryItem newDairy = new Dairy(specialCase,price, foodCode,volume);
					shoppingList.push(newDairy);
				}
				else
				{
					if(!scanner.hasNextDouble()) {
					item += " " +scanner.next();	
					}
					price = scanner.nextDouble();
					GroceryItem newItem = new GroceryItem(item,price,foodCode); 
					shoppingList.push(newItem);
				}
			}
			scanner.close();
	}
	/**
	 * Method that clears the list of GroceryItems
	 */
	public void clear() {
		shoppingList.clear();
	}
	/**
	 * Method that clears the screen area of any previously displayed GroceryItems
	 * @param area is a specified JTextArea that will display this groceryCart
	 */
	public void ScreenClear(JTextArea area) {
		area.setText(null);
	}
	/**
	 * Method uses the parameter GroceryItem item and adds it to the ArrayList created in this class
	 * @param item is a GroceryItem that will be added to the list of GroceryItems
	 */
	public void addItem(GroceryItem item) { shoppingList.push(item); }
	/**
	 * Return method to remove the top item in the GroceryCart and creates a reference pointer to it
	 * will be used to transfer GroceryItems from the Cart to the Register after
	 * @param other is the reference pointer that will be accessed by the scan food button 
	 * @return other the reference pointer to removed GroceryItem
	 */
    public GroceryItem removeTopItem(GroceryItem other) {
        if (!shoppingIter.hasPrevious()){
            other = shoppingList.pop();// Removes the first item
        }
        else {
            shoppingIter.previous();
        }
        return other;
    }
	/**
	 * Method to display created GroceryItems list to a JTextArea
	 * @param area is the specified JTextArea which the list is going be displayed on
	 */
	public void display(JTextArea area) {
		for(int i =shoppingList.size()-1 ; i >=0; i--){ // runs through for loop to go through every element in list
            area.append((shoppingList.get(i).getLabel()) + "\n"+"\n"); // appends it to the JTextArea with proper formatting
        }
	}
	/**
	 *  Return method that tells if the cart is empty or not
	 *  used to insure that the scan food button will not create an error when pressed with no items left in the list 
	 *  @return true when list is empty
	 *  @return false when list is not empty
	 */
	public boolean isCartEmpty() {
		if (shoppingList.isEmpty())
		{
			return true;
		}
		else return false;
	}
	
	/**
	 * Return Method that gets the next item in the list used for the nutrition scanner later implemented to program
	 * @param groceryQueue 
	 * @param other 
	 */
	public GroceryItem getNextItem (int groceryQueue,GroceryItem other) {

            other = shoppingList.get(groceryQueue);

        return other;
	}

	/**
	 * Return Method that gets the size of the list used to return the size of the list, used in nurtition scanner to figure out how many objects are in the list
	 * @return shoppingList.size(); size of the list 
	 */
	public int getNumbItems() {
	    return shoppingList.size();
	}
}
