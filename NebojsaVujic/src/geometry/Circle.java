package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{

	private Point center;
	private int radius;
	private Color color;
	private Color innerColor;
	
	//metode pristupa 
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this.center = center;
		this.radius = radius;
		setSelected(selected);
	}
	public Circle(Point center, int radius, boolean selected,Color color) {
		this(center,radius,selected);
		this.color=color;
	}
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.innerColor=innerColor;
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}
	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Circle) {
			Circle shapeToCompare = (Circle)obj;
			return (int)(this.area() - shapeToCompare.area());
		}
		return 0;
	}


	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if (radius < 0)
			throw new Exception("Radius ne sme biti negativna vrednost!");
		this.radius = radius;
	}
	//povrsina i obim
	
	public double area() { 
		return radius*radius*Math.PI;
	}
	public double circumference() { 
		return 2*radius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj;
			if (this.center.equals(pomocna.center) && this.radius == pomocna.radius)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public String toString() {
		return "Center: " + center + ", radius = " + radius;
	}
	
	public boolean contains (int x, int y) {
		return center.distance(new Point(x,y)) <= radius;
	}
	
	public boolean contains (Point p) { 
		//return center.distance(p) <= radius;
		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}
	public void fill(Graphics g) {
		g.setColor(this.innerColor);
		g.fillOval(this.center.getX() - radius	 + 1, this.center.getY() - radius + 1, radius*2 - 2, radius*2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		// obratiti paznju na potpis metode drawOval!
		g.drawOval(center.getXCoordinate()-radius,
				center.getYCoordinate() - radius, 2* radius, 2*radius);
		this.fill(g);
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getXCoordinate() - 2,
					center.getYCoordinate() - 2, 4, 4); //oko centra
			g.drawRect(center.getXCoordinate() - 2 - radius,
					center.getYCoordinate() - 2, 4, 4); //levo
			g.drawRect(center.getXCoordinate() - 2 + radius,
					center.getYCoordinate() - 2, 4, 4); //desno
			g.drawRect(center.getXCoordinate() - 2,
					center.getYCoordinate() - 2 - radius, 4, 4); //gore
			g.drawRect(center.getXCoordinate() - 2,
					center.getYCoordinate() - 2 + radius, 4, 4); //dole
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