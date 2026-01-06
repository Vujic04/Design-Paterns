package hexagonAdapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {
	
	
	private final Hexagon hexagon;
	
	
	
	public HexagonAdapter(int x, int y, int r, boolean selected, Color border, Color innerColor) {
		hexagon = new Hexagon (x,y,r);
		hexagon.setSelected(selected);
		hexagon.setBorderColor(border);
		hexagon.setAreaColor(innerColor);
	}
	
	public HexagonAdapter(int x, int y, int r) {
		this(x,y,r,false,Color.BLACK,Color.WHITE);
	}
	
	
	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY() + y);
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		hexagon.paint(g);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getX() { return hexagon.getX();}
	public int getY() { return hexagon.getY();}
	public int getRadius() { return hexagon.getR();}
	
	
	public void setX(int x) { hexagon.setX(x);}
	public void setY(int y) { hexagon.setY(y);}
	public void setRadius(int r) { hexagon.setR(r);}
	
	
	public Color getAreaColor() { return hexagon.getAreaColor();}
	public Color getBorderColor() { return hexagon.getBorderColor();}
	
	public void setAreaColor(Color c) { hexagon.setAreaColor(c);}
	public void setBorderColor(Color c) { hexagon.setBorderColor(c);}
	
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		hexagon.setSelected(selected);
	}
	
	public boolean isSelected() {
		return hexagon.isSelected();
	}

}
