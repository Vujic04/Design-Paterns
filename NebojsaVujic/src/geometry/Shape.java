package geometry;

import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable{

	private boolean selected;
	
	public Shape() {
		
	}
	public Shape(boolean selected) {
		this.selected = selected;
	}
	
	//apstraktne metode - nemaju implementaciju, samo potpis metode
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}