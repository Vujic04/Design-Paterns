package controller;

import java.awt.Color;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagonAdapter.HexagonAdapter;

public class ShapeFormat {
	
	private ShapeFormat() {}
	
	private static String hex(Color c) {
	    if (c == null) return "null";
	    return String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
	}
	
	
	public static String describeShape(Shape s) {
	    if (s instanceof Point p) {
	        return String.format("Point x=%d y=%d color=%s",
	                p.getXCoordinate(), p.getYCoordinate(), hex(p.getColor()));
	    }
	    if (s instanceof Line l) {
	        return String.format("Startpoint=(%d,%d) endpoint=(%d,%d) color=%s",
	                l.getStartPoint().getXCoordinate(), l.getStartPoint().getYCoordinate(),
	                l.getEndPoint().getXCoordinate(), l.getEndPoint().getYCoordinate(),
	                hex(l.getColor()));
	    }
	    if (s instanceof Circle c) {
	        return String.format("Circle center=(%d,%d) r=%d outline=%s inner=%s",
	                c.getCenter().getXCoordinate(), c.getCenter().getYCoordinate(),
	                c.getRadius(), hex(c.getColor()), hex(c.getInnerColor()));
	    }
	    if (s instanceof Donut d) {
	        return String.format("Donut center=(%d,%d) r=%d innerR=%d outline=%s inner=%s",
	                d.getCenter().getXCoordinate(), d.getCenter().getYCoordinate(),
	                d.getRadius(), d.getInnerRadius(),
	                hex(d.getColor()), hex(d.getInnerColor()));
	    }
	    if (s instanceof Rectangle r) {
	        return String.format("Rectangle =(%d,%d) w=%d h=%d outline=%s inner=%s",
	                r.getUpperLeftPoint().getXCoordinate(), r.getUpperLeftPoint().getYCoordinate(),
	                r.getWidth(), r.getHeight(),
	                hex(r.getColor()), hex(r.getInnerColor()));
	    }
	    if (s instanceof HexagonAdapter h) {
	        return String.format("Hexagon x=%d y=%d r=%d border=%s area=%s",
	                h.getX(), h.getY(), h.getRadius(),
	                hex(h.getBorderColor()), hex(h.getAreaColor()));
	    }
	    return s.toString();
	}
}
