import java.util.ArrayList;
import javax.swing.JTextArea;
import java.util.Date;
import java.text.DecimalFormat;
/**
 * Calvin Yap
 * #500825267
 * Class Cash Register is a subclass to GroceryItem, and is used to create the right panel of a CashRegister
 * that you scan items into and it will compute the total
 */
public class CashRegister extends GroceryItem {
	private ArrayList<GroceryItem> checkOut = new ArrayList<GroceryItem>();// AraayList of GrcoceryItems, empty to begin with and will have elements added to it when they are scanned from GroceryCart
	private static DecimalFormat twoPlaces = new DecimalFormat("#.##"); // 
	Date date = new Date();
	/**
	 * 
	 */
	public void scanItem(GroceryItem item) {
		checkOut.add(item);
	}
	/**
	 * 
	 */
	public void display(JTextArea area) {
		double sum=0; 
		for (int i =0;i< checkOut.size();i++)
		{
		sum = sum +checkOut.get(i).getPrice();		
		area.append(checkOut.get(i).getLabel() +"   " + twoPlaces.format(checkOut.get(i).getPrice()) +"\n"+"\n");
		}
		area.append("---------------------"+"\n");
		area.append("Total:  "+twoPlaces.format(sum));
		area.append("\n" + date.toString());
	}
	/**
	 * 
	 */
	public void clear() {
		checkOut.clear();
	}
	/**
	 * 
	 */
	public void ScreenClear(JTextArea area) {
		area.setText(null);
	}

}
