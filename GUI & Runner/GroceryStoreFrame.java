import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
/**
 * Calvin Yap
 * #500825267
 * Main JFrame class which constructs the display of the whole project and is interactive with GUI, lines will be all documented
 * to show what each section does
 * 
 */
public class GroceryStoreFrame extends JFrame {
	// Sets the size of the application screen
	private static final int FRAME_WIDTH= 1200;  // Width of the application screen 
	private static final int FRAME_HEIGHT =1000; // Height of the application screen
	// new Grocery components such as a new Cart, Register, and Grocery item
	ConveyerBelt belt = new ConveyerBelt();
	private GroceryCart allItems = new GroceryCart(); // 
	private CashRegister register = new CashRegister();
	private GroceryItem temp = new GroceryItem();
	private int groceryCounter = 0; // Counter to tell which item have, it is basically a queue for the items
	
	// A default size for the jtextareas
	
	// all the Jcomponents that is needed in the program
	private JPanel cartPanel;
	private JPanel scanPanel;
	private JPanel cashRegisterPanel;
	private JPanel nutritionPanel;
	// GroceryCart 
	private JLabel groceryCartLabel = new JLabel ("Grocery Cart"); // Title at the top 
	private JTextArea cartDisplayArea= new JTextArea(30,20); // Display area for GroceryItems
	private JButton refill = new JButton("REFILL"); // Refill Button
	private JScrollPane cartScroll = new JScrollPane(cartDisplayArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //  Scrollable text area
	// ScanButton
	private JButton scanGroceryItemBut= new JButton ("SCAN"); // Scan item button
	// CashRegister
	private JLabel cashRegisterLabel = new JLabel ("Cash Register"); // Title at the top
	private JButton checkoutButton = new JButton ("CHECKOUT"); // Checkout button
	private JTextArea registerTotalDisplay = new JTextArea(30,20); // Display for CashRegister
	private JScrollPane totalScroll = new JScrollPane(registerTotalDisplay); // Scrollable text area
	//NutritionPanel 
	private JLabel nutritionLabel = new JLabel ("Cart Nutrition Information"); // Title at the bottom
	private JButton scanFoodButton = new JButton ("SCAN FOOD ITEM"); // Scan food item button
	private JButton clearButton = new JButton ("CLEAR"); // a clear Button
	private JTextArea nutritionFactsDisplay = new JTextArea(100,20); // display area for Nutrition Scanner
	private JButton calsButton = new JButton ("CALS"); // Cals sorting button
	private JButton carbsButton = new JButton ("CARBS"); // Carbs sorting button
	private JButton fatButton = new JButton ("FAT"); // fat sorting button
	private JButton sugarButton = new JButton ("SUGAR"); // sugar sorting button
	private JScrollPane nutritionScroll = new JScrollPane(nutritionFactsDisplay); // scrollable text area
	private JButton pickUpButton = new JButton("PICK UP");
	private JButton addButton = new JButton("ADD");

	/**
	 * Class to create the initial grocery Store Frame, will call on other helper methods to construct each section
	 */
	public GroceryStoreFrame() {
		createControlPanels();
		setSize(FRAME_WIDTH, FRAME_HEIGHT); // set the size of the application 
	}
	
	private void createControlPanels()
	   {
		   createGroceryCartPanel(); // call to helper to create GroceryCart section
			createCashRegisterPanel(); // call to helper to create CashRegister section
			createScanItemsPanel(); // Call to helper to create the item scanner button
			createNutritionScannerPanel(); // call to helper to create the nutrition scanner section
	   }
	
	private void createGroceryCartPanel() { 
		// add in the 3 labels and buttons
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());
		cartPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JPanel cartControlPanel = new JPanel();
		cartControlPanel.add(groceryCartLabel);
		cartControlPanel.add(refill);	      	      	     	      	   
		cartPanel.add(cartScroll,BorderLayout.CENTER);
		cartPanel.add(cartControlPanel,BorderLayout.NORTH);
	    // Changing the font 
		cartDisplayArea.setFont(new Font("Arial", Font.BOLD, 20));
		groceryCartLabel.setFont(new Font("Arial", Font.BOLD, 35));
		refill.setFont(new Font("Arial", Font.BOLD, 13));		
	    // setting a non editable text area
		cartDisplayArea.setEditable(false);		
		// What it does
		try {
			try {
				allItems.fill();
			} catch (IOException e) {
			System.out.println("Issue with the file, either not found or corupted");
			System.exit(0);
			}
		} catch (InputMismatchException e) {
			System.out.println("Issue with the reading the file, thre is a mismatch in Scanner");
			System.exit(0);
		} 
		// fills the cart
		allItems.display(cartDisplayArea); // displays the cart
			// Class for button 
			class refillListen implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					allItems.ScreenClear(cartDisplayArea); // clear the screen
					allItems.clear(); // clear the GroceryCart
					try {
						try {
							allItems.fill();
						} catch (IOException e) {
						System.out.println("Issue with the file, either not found or corupted");
						System.exit(0);
						}
					} catch (InputMismatchException e) {
						System.out.println("Issue with the reading the file, thre is a mismatch in Scanner");
						System.exit(0);
					} // fill it back up again
					
					allItems.display(cartDisplayArea); // display it
	    			groceryCounter =0; // Reset item back to the top

				}	
			}
		ActionListener refillButtonListener = new refillListen();
		refill.addActionListener(refillButtonListener); // add action to the button	
		getContentPane().add(cartPanel, BorderLayout.WEST);
	}

	private void createScanItemsPanel() {
       scanPanel = new JPanel(); 
       scanPanel.setLayout(new BorderLayout());
	   JPanel groceryScannerPanel = new JPanel();
	   groceryScannerPanel.add(pickUpButton,BorderLayout.NORTH);
	   groceryScannerPanel.add(addButton,BorderLayout.NORTH);
	   groceryScannerPanel.add(scanGroceryItemBut,BorderLayout.NORTH);
	   scanPanel.add(groceryScannerPanel, BorderLayout.NORTH);
	   getContentPane().add(scanPanel,BorderLayout.CENTER);
	   scanGroceryItemBut.setFont(new Font("Arial", Font.BOLD, 13));
	   pickUpButton.setFont(new Font("Arial", Font.BOLD, 13));
	   addButton.setFont(new Font("Arial", Font.BOLD, 13));
	   scanPanel.add(belt);
	    	// what it does
	    	class scanListen implements ActionListener{	    		
				public void actionPerformed(ActionEvent event) {
				if(belt.isEmpty() == false ) {
					temp = belt.removeItem();
					register.scanItem(temp); // scan the item in to the CashRegister
					
				}	
				    register.ScreenClear(registerTotalDisplay); // clear the cashRegister display
				    register.display(registerTotalDisplay); // display new after element is added
				}
	    	}
	    ActionListener scanListen = new scanListen();
	    scanGroceryItemBut.addActionListener(scanListen); // adds action to button
	    	class pickUpListen implements ActionListener{
	    		public void actionPerformed(ActionEvent event) {
		    		if (allItems.isCartEmpty() == false && belt.isPickup() == false ) {
			    		temp = allItems.removeTopItem(temp);
			    		belt.setPickedUpItem(temp);
			    		allItems.ScreenClear(cartDisplayArea); // clear the GroceryCart display
						allItems.display(cartDisplayArea); // display new after element is removed
							belt.changeAdd();
			    	}	
	    		}
	    	}
	    	class addListen implements ActionListener{
	    		public void actionPerformed(ActionEvent event) {
	    			if(belt.isOverBelt() == true && belt.added() == false) {
	    			belt.addItem();
	    			belt.changePickup();
	    			}
	    		}
	    	}
	    ActionListener addListen = new addListen();
	    addButton.addActionListener(addListen);
	    ActionListener pickUpListen = new pickUpListen();
	    pickUpButton.addActionListener(pickUpListen);
	   }
	
	private void createCashRegisterPanel() {	
		cashRegisterPanel = new JPanel();
		cashRegisterPanel.setLayout(new BorderLayout());
	    cashRegisterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	    JPanel CRControlPanel = new JPanel();
	    CRControlPanel.add(cashRegisterLabel);
	    CRControlPanel.add(checkoutButton);
	    // changing the font
	    registerTotalDisplay.setFont(new Font("Arial", Font.BOLD, 20));
	    checkoutButton.setFont(new Font("Arial", Font.BOLD, 13));
	    cashRegisterLabel.setFont(new Font("Arial", Font.BOLD, 35));		
	    // adding in the scroll bars
	    totalScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    totalScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    cashRegisterPanel.add(CRControlPanel,BorderLayout.NORTH);
	    cashRegisterPanel.add(totalScroll,BorderLayout.CENTER);
	    getContentPane().add(cashRegisterPanel,BorderLayout.EAST);
	    // making text area non editable
		registerTotalDisplay.setEditable(false);
			// What it does
			class checkoutListen implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					register.clear(); // clears the register
					register.ScreenClear(registerTotalDisplay); // clears display of the register
				}		
			}
		ActionListener checkoutListen = new checkoutListen();
		checkoutButton.addActionListener(checkoutListen); // adds action to button
	}
	public void createNutritionScannerPanel() {
		nutritionPanel = new JPanel();
		nutritionPanel.setLayout(new BorderLayout());
	    nutritionPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	    nutritionPanel.setPreferredSize(new Dimension(400, 400));
	    nutritionPanel.add(nutritionScroll,BorderLayout.CENTER);
	   JPanel scanPanel = new JPanel();
	   scanPanel.add(nutritionLabel);
	   scanPanel.add(scanFoodButton);
	   scanPanel.add(clearButton);
	   nutritionPanel.add(scanPanel,BorderLayout.NORTH);
	   JPanel sorterButtons = new JPanel();
	   sorterButtons.add(calsButton);
	   sorterButtons.add(carbsButton);
	   sorterButtons.add(fatButton);
	   sorterButtons.add(sugarButton);
	   nutritionPanel.add(sorterButtons,BorderLayout.SOUTH);
	   getContentPane().add(nutritionPanel,BorderLayout.SOUTH);
	   // Change the font
	    nutritionFactsDisplay.setFont(new Font("Arial", Font.BOLD, 20));
	    sugarButton.setFont(new Font("Arial", Font.BOLD, 13));
	    fatButton.setFont(new Font("Arial", Font.BOLD, 13));
	    calsButton.setFont(new Font("Arial", Font.BOLD, 13));
	    carbsButton.setFont(new Font("Arial", Font.BOLD, 13));
	    clearButton.setFont(new Font("Arial", Font.BOLD, 13));
	    scanFoodButton.setFont(new Font("Arial", Font.BOLD, 13));
	    nutritionLabel.setFont(new Font("Arial", Font.BOLD, 35));
	    // add in scrollable text area
	    nutritionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    // creates a new NutritionScanner 
	    NutritionScanner scan = new NutritionScanner();
	    	// what it does
	    	class scanListen implements ActionListener{
	    		public void actionPerformed(ActionEvent event){
	    			if(groceryCounter < allItems.getNumbItems()) { // makes sure that it wont have a null pointer exception when there is no more items in the Cart
	    			scan.ScreenClear(nutritionFactsDisplay); // clear the display area of the nutrition panel
	    			temp = allItems.getNextItem(groceryCounter,temp); // Set temp GroceryItem to the next item, groceryCounter makes sure its the right element
	    			scan.scanFoodItem(temp.getFoodCode());	// get the food code of the temp and match it with the correct food type
	    			scan.displayAll(nutritionFactsDisplay); // display it back out to the panel
	    			groceryCounter+=1; // adds one to counter so it moves up the cart without removing
	    			}
				}	    		
	    	}
	    	ActionListener scanListen = new scanListen();
	    	scanFoodButton.addActionListener(scanListen); //adds action to button
	    	// what it does
	    	class clearListen implements ActionListener{
	    		public void actionPerformed(ActionEvent event){
	    			scan.resetMeasure(); // resets the measure as the nutrition cart is rest
	    			scan.ScreenClear(nutritionFactsDisplay); // clear the display
	    			scan.clear(); // clear the list of FoodTypes
	    			groceryCounter =0; // Reset item back to the top
				}	    
	    	}
	    	ActionListener clearListen = new clearListen();
	    	clearButton.addActionListener(clearListen); //adds action to button
	    	// what it does
	    	class caloriesListen implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					scan.ScreenClear(nutritionFactsDisplay); // Clears the nutrition display panel
					Collections.sort(scan.getList()); // sort the list according to calories
					scan.displayAll(nutritionFactsDisplay); // Display back when its sorted
				}
	    	}
	    	ActionListener caloriesListen = new caloriesListen();
	    	calsButton.addActionListener(caloriesListen); //adds action to button
	    	// what it does
	    	class carbsListen implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					scan.ScreenClear(nutritionFactsDisplay);// Clears the nutrition display panel
					Collections.sort(scan.getList(), new FoodTypeCarbsComparator()); // sort the list according to carbs
					scan.displayAll(nutritionFactsDisplay);// Display back when its sorted
				}
	    	}
	    	ActionListener carbsListen = new carbsListen();
	    	carbsButton.addActionListener(carbsListen); //adds action to button
	    	// what it does
	    	class fatListen implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					scan.ScreenClear(nutritionFactsDisplay);// Clears the nutrition display panel
					Collections.sort(scan.getList(), new FoodTypeFatComparator());  // sort the list according to Fat
					scan.displayAll(nutritionFactsDisplay);// Display back when its sorted
				}
	    	}
	    	ActionListener fatListen = new fatListen();
	    	fatButton.addActionListener(fatListen); //adds action to button
	    	// what it does
	    	class sugarListen implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					scan.ScreenClear(nutritionFactsDisplay);// Clears the nutrition display panel
					Collections.sort(scan.getList(), new FoodTypeSugarComparator());  // sort the list according to sugar
					scan.displayAll(nutritionFactsDisplay);// Display back when its sorted
				}
	    	}
	    	ActionListener sugarListen = new sugarListen();
	    	sugarButton.addActionListener(sugarListen); //adds action to button
	}	
}
