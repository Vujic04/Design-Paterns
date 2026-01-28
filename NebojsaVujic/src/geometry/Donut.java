package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

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
	@Override
	public Color getColor() {
	    return color;
	}

	@Override
	public void setColor(Color color) {
	    this.color = color;
	}

	@Override
	public Color getInnerColor() {
	    return innerColor;
	}

	@Override
	public void setInnerColor(Color innerColor) {
	    this.innerColor = innerColor;
	}

	
	public void draw(Graphics g) {
		
		Graphics2D g2= (Graphics2D) g;
		
		int cx = getCenter().getXCoordinate();
		int cy = getCenter().getYCoordinate();
		
		int outerRadius = getRadius();
		int innerRadius = getInnerRadius();
		
		Ellipse2D outer = new Ellipse2D.Double(cx - outerRadius, cy-outerRadius, 2.0 * outerRadius, 2.0 *outerRadius);
		Ellipse2D inner = new Ellipse2D.Double(cx - innerRadius, cy-innerRadius, 2.0 * innerRadius, 2.0 *innerRadius);
		
		Area ring = new Area(outer);
		ring.subtract(new Area(inner));
		
		if(getInnerColor() !=null) {
			g2.setColor(getInnerColor());
			g2.fill(ring);
		}
		
		if(getColor() != null) {
			g2.setColor(getColor());
			g2.draw(outer);
			g2.draw(inner);
		}
		
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