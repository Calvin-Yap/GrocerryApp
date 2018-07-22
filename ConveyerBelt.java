import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class ConveyerBelt extends JComponent implements MouseMotionListener, MouseListener  {
	private LinkGroceryItem first;
	private LinkGroceryItem pickedUpItem = null;
	private LinkGroceryItem temp;
	private boolean hasItem = false;
	private boolean hasAdded = false;
	private boolean mouseDragged;
	private boolean addedAfter= false;
	int mouseX,mouseY;
	private int counter =0;
	public ConveyerBelt() {
		addMouseMotionListener(this);
	}
	//setPickup(50,300,500,30);
	//addMouseMotionListener(this);
	public void paintComponent(Graphics g) {
		g.fillRect(50, 300,500, 30);

		if (mouseDragged && first!=null) {	
			if (pickedUpItem != null) {
			g.drawString(pickedUpItem.gitem.getLabel(), mouseX+5, mouseY+20);
			g.drawRect(mouseX,mouseY,pickedUpItem.box.width,pickedUpItem.box.height);
			}
			repaint();
		}
		else {
			if (pickedUpItem != null && hasItem ==true) {
			g.drawString(pickedUpItem.gitem.getLabel(), 60, 80);
			((Graphics2D) g).draw(pickedUpItem.box);
			}
			repaint();

		}
			if(first != null) {
				first.draw((Graphics2D) g);
			}
	}

	public boolean isPickup() {
		return hasItem;
	}
	public boolean added() {
		return hasAdded;
	}
	public void changePickup() {
		hasItem = false;
	}
	public void changeAdd() {
		hasAdded =false;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	private int numItems() {
		int count = 0;
		for (LinkGroceryItem counter=first; counter!=null; counter=counter.next) {
			count++;
		}
		return count;
	}
	public boolean isOverBelt() {
		if (numItems() <=4) {
			return true;
		}
		else return false;
	}
	public void addItem() {
		
		if (pickedUpItem != null ) {
			LinkGroceryItem putFirst = new LinkGroceryItem(pickedUpItem.gitem,first);
			first = putFirst;
			hasAdded=true;
			counter ++;
			repaint();
		}
	}
	
	public GroceryItem removeItem() {
		LinkGroceryItem previousToNull = new LinkGroceryItem();
		LinkGroceryItem last = first;		
		while(last.next!=null) {
			previousToNull = last;
			last = last.next;
		}
		previousToNull.next = null;
		
		if (last.gitem == first.gitem) {
			first = null;
		}
		counter--;
		repaint();
		return last.gitem;
	}
	
	public void setPickedUpItem(GroceryItem item) {
		LinkGroceryItem settedPickup= new LinkGroceryItem(item,first);
		pickedUpItem = settedPickup;
		hasItem = true;
		repaint();
	
	}
	public void movePickUp(int x, int y)
	   {
		LinkGroceryItem pickUp = pickedUpItem; 
	      if (pickUp != null)
	         pickUp.box.setLocation(x, y);
	      repaint();
	   }
		class LinkGroceryItem{
			private GroceryItem gitem;
			private LinkGroceryItem next;
			private Rectangle box;
			
			LinkGroceryItem(){
				gitem = null;
				next= null;
				temp = null;
				box = null;
			}
			
			LinkGroceryItem(GroceryItem otherI, LinkGroceryItem otherL){
				gitem = otherI;
				next = otherL;
				box = new Rectangle(55,65,70,40);
				}
			public void setLocation(int x, int y) {
				box.setLocation(x, y);
			}
			
			public boolean intersects(LinkGroceryItem item) {
				if(pickedUpItem !=null) {
				Rectangle test = new Rectangle(mouseX,mouseY,pickedUpItem.box.width, pickedUpItem.box.height);
				changePickup();
				return test.intersects(this.box);
				}
				else return false;
			}
			
			public void draw(Graphics2D g2) {
				int counter  = 2;
					temp = first;
					if(first!=null){
							g2.drawString(first.gitem.getLabel(),80,280);
							first.box.setLocation(75,260);
							g2.draw(first.box);
						while(temp.next!=null) {
							g2.drawString(temp.next.gitem.getLabel(),(counter*60)+15*counter+5,280);
							temp.next.setLocation((counter*60)+15*counter,260);
							g2.draw(temp.next.box);
							temp = temp.next;
							counter++;
						}
					}		
			}	
		}
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
		}
		public void mouseReleased(MouseEvent e) {
			
		}
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			mouseDragged = true;
			ConveyerBelt belt = new ConveyerBelt();
			LinkGroceryItem temp= first ;
			if(first != null) {
			while(temp.next!=null) {
			temp = temp.next;
			}
				if (counter == 5) {
					mouseDragged = false;
				}
				else if(temp.intersects(pickedUpItem) && addedAfter ==false && counter !=5) {
						LinkGroceryItem newItem = new LinkGroceryItem(pickedUpItem.gitem,first.next);
						first.next = newItem;
						addedAfter =true;
						pickedUpItem = null;
						counter ++;
						repaint();	
				}	
			}
		}

		
		public void mouseMoved(MouseEvent e) {	
			mouseDragged = false;
			addedAfter= false;
		}
		
		
}
