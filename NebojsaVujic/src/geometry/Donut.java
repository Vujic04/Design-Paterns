package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {
	
	int radius;
	int innerRadius;
	private Color color;
	private Color innerColor;

	//konustruktori
	public Donut() {
		
	}
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		super(center, radius, selected);
		this.innerRadius = innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		this.color=color;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		this.innerColor=innerColor;
	}
	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Donut) {
			Donut shapeToCompare = (Donut)obj;
			return (int)(this.area() - shapeToCompare.area());
		}
		return 0;
	}
	
	//get i set metode
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	@Override
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut)
		{
			Donut pomocna = (Donut) obj;
			if (this.getCenter().equals(pomocna.getCenter()) &&
					this.getRadius() == pomocna.getRadius() &&
					innerRadius == pomocna.getInnerRadius())
				return true;
			else 
				return false;
		} else 
			return false;
		
	}
	@Override
	public String toString() {
		//Center = (x,y), radius = r, innerRadius = ir 
		return super.toString() + ", innerRadius = " + innerRadius;
	}
	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y) && this.getCenter().distance(new Point(x,y)) >= innerRadius;
	}
	@Override
	public boolean contains(Point p) {
		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}
	public void fill(Graphics g) {
		g.setColor(this.innerColor);
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		super.draw(g);
		this.fill(g);
		g.setColor(this.color);
		g.drawOval(getCenter().getXCoordinate()-innerRadius,
				getCenter().getYCoordinate()-innerRadius, 
				2*innerRadius, 2*innerRadius);

		g.drawOval(getCenter().getXCoordinate()-radius,
				getCenter().getYCoordinate()-radius, 
				2*radius, 2*radius);
		
		if(this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getXCoordinate() - 2 - innerRadius,
					getCenter().getYCoordinate() - 2, 4, 4); //levo
			g.drawRect(getCenter().getXCoordinate() - 2 + innerRadius,
					getCenter().getYCoordinate() - 2, 4, 4); //desno
			g.drawRect(getCenter().getXCoordinate() - 2,
					getCenter().getYCoordinate() - 2 - innerRadius, 4, 4); //gore
			g.drawRect(getCenter().getXCoordinate() - 2,
					getCenter().getYCoordinate() - 2 + innerRadius, 4, 4); //dole
		}
	}
}