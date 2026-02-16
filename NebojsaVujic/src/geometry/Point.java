package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	private int xCoordinate;
	private int yCoordinate;
	private Color color;
	
	// default-ni konstruktor
	public Point() {
		
	}
	// konstruktori sa parametrima
	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate);
		//this.selected = selected;
		setSelected(selected);
	}
	public Point(int xCoordinate, int yCoordinate, boolean selected, Color color) {
        this(xCoordinate, yCoordinate, selected);
        this.color = color;
    }
	@Override
	public void moveTo(int x, int y) {
		this.xCoordinate=x;
		yCoordinate=y;
	}
	
	@Override
	public void moveBy(int x, int y) {
		xCoordinate+=x;
		yCoordinate+=y;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point shapeToCompare = (Point)o;
			return (int)this.distance(new Point(0,0))
					- (int)shapeToCompare.distance(new Point(0,0));
		}
		return 0;
	}

	
	// Get i set metode
	public int getXCoordinate() {
		return xCoordinate;
	}
	public void setXCoordinate(int newX) {
		xCoordinate = newX;
	}
	public int getYCoordinate() {
		return yCoordinate;
	}
	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	 public Color getColor() {
	    return color;
	}

	public void setColor(Color color) {
	    this.color = color;
	}
	
	public double distance(Point secondPoint) {
		int dx = secondPoint.xCoordinate - this.xCoordinate;
		int dy = secondPoint.yCoordinate - this.yCoordinate;
		double result = Math.sqrt(dx*dx + dy*dy);
		return result;
	}
	public String toString() {
		// (x,y)
		return "(" + xCoordinate + "," + yCoordinate + ")";
	}
	// point1 == point2 vrsi se poredjenje po referenci
	// point1.equals(point2) vrsi se poredjenje po jednakosti
	public boolean equals(Object obj) {
		if(obj instanceof Point) //da li je obj objekat klase Point?
		{
			Point pomocna = (Point) obj;
			if (this.xCoordinate == pomocna.xCoordinate &&
					this.yCoordinate == pomocna.yCoordinate) 
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		return this.distance(new Point(x,y)) <= 2;	
	}
	
	public boolean contains(Point p) {
		return this.distance(p) <= 2;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		//horizontalna linija
		g.drawLine(xCoordinate-2, yCoordinate, xCoordinate+2, yCoordinate);
		
		//vertikalna linija
		g.drawLine(xCoordinate, yCoordinate-2, xCoordinate, yCoordinate+2);
		
		if(this.isSelected()) {
			// crtam kvadratic plave boje 4x4 piksela
			g.setColor(Color.blue);
			g.drawRect(xCoordinate - 2, yCoordinate - 2, 4, 4);
		}
	}
	public void setX(int xPoint) {
		// TODO Auto-generated method stub
		
	}
	public void setY(int yPoint) {
		// TODO Auto-generated method stub
		
	}
	public int getX() {
		// TODO Auto-generated method stub
		return xCoordinate;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return yCoordinate;
	}
	public Point clone() {
		return new Point(this.xCoordinate,this.yCoordinate,this.isSelected(),this.color);
	}
	public void applyForm(Point p) {
		this.xCoordinate=p.xCoordinate;
		this.yCoordinate=p.yCoordinate;
		this.color=p.color;
	}
}