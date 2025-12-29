package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;
	private Color color;
	private Color innerColor;
	
	//konstruktori
	public Rectangle() {
		
	}
	public Rectangle(Point upperLeftPoint, int w, int h ) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = w;
		this.height = h;
	}
	public Rectangle(Point upperLeftPoint, int w, int h, boolean selected ) {
		this(upperLeftPoint,w,h);
		setSelected(selected);
	}
	public Rectangle(Point upperLeftPoint, int w, int h, boolean selected, Color color ) {
		this(upperLeftPoint,w,h,selected);
		this.color=color;
	}
	public Rectangle(Point upperLeftPoint, int w, int h, boolean selected, Color color,Color innerColor ) {
		this(upperLeftPoint,w,h,selected,color);
		this.innerColor=innerColor;
	}
	
	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}
	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle shapeToCompare = (Rectangle)obj;
			return this.area() - shapeToCompare.area();
		}
		return 0;
	}
	
	//metode pristupa 
	
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	//povrsina i obim
	public int area() {
		return width * height;
	}
	public int circumference() {
		return 2*width + 2*height;
	}
	public String toString() {
		//Upper left point: (xUpperLeft, yUpperLeft), width = <width>, height = <height> 
		return "Upper left point: " + upperLeftPoint + ", width = " + width +
				", heigh = " + height;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			
			if(this.upperLeftPoint == pomocna.upperLeftPoint &&
					this.width == pomocna.width 
					&& this.height == pomocna.height)
				return true;
			else 
				return false;
		} else 
			return false;
	}
	
	public boolean contains(int x, int y) {
		return (x > upperLeftPoint.getXCoordinate() 
				&& x < upperLeftPoint.getXCoordinate() + width
				&& y >upperLeftPoint.getYCoordinate() 
				&& y < upperLeftPoint.getYCoordinate() + height);
	}
	public boolean contains (Point p) { 
		
		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}
	public void fill(Graphics g) {
		g.setColor(this.innerColor);
		g.fillRect(this.upperLeftPoint.getX()+1, this.getUpperLeftPoint().getY()+1, width-1, height-1);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawRect(upperLeftPoint.getXCoordinate(),
				upperLeftPoint.getYCoordinate(), width, height);
		this.fill(g);
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getXCoordinate() - 2,
					upperLeftPoint.getYCoordinate() -2, 4, 4); //gore levo
			g.drawRect(upperLeftPoint.getXCoordinate() - 2 + width,
					upperLeftPoint.getYCoordinate() -2, 4, 4); //gore desno
			g.drawRect(upperLeftPoint.getXCoordinate() - 2,
					upperLeftPoint.getYCoordinate() -2 + height, 4, 4); //dole levo
			g.drawRect(upperLeftPoint.getXCoordinate() - 2 + width,
					upperLeftPoint.getYCoordinate() -2 + height, 4, 4); //dole desno
		}
		
	}
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	public Color getInnerColor() {
		// TODO Auto-generated method stub
		return innerColor;
	}
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color=color;
	}
	public void setInnerColor(Color innerColor) {
		// TODO Auto-generated method stub
		this.innerColor=innerColor;
	}
	
}