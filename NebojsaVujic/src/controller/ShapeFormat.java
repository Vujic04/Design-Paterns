package controller;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagonAdapter.HexagonAdapter;

public class ShapeFormat {

	private ShapeFormat() {
	}

	private static String hex(Color c) {
		if (c == null)
			return "null";
		return String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
	}

	public static String describeShape(Shape s) {
		if (s instanceof Point p) {
			return String.format("Point x=%d y=%d color=%s", p.getXCoordinate(), p.getYCoordinate(), hex(p.getColor()));
		}
		if (s instanceof Line l) {
			return String.format("Line start=(%d,%d) end=(%d,%d) color=%s", l.getStartPoint().getXCoordinate(),
					l.getStartPoint().getYCoordinate(), l.getEndPoint().getXCoordinate(),
					l.getEndPoint().getYCoordinate(), hex(l.getColor()));
		}
		if (s instanceof Donut d) {
			return String.format("Donut center=(%d,%d) r=%d innerR=%d outline=%s inner=%s",
					d.getCenter().getXCoordinate(), d.getCenter().getYCoordinate(), d.getRadius(), d.getInnerRadius(),
					hex(d.getColor()), hex(d.getInnerColor()));
		}
		if (s instanceof Circle c) {
			return String.format("Circle center=(%d,%d) r=%d outline=%s inner=%s", c.getCenter().getXCoordinate(),
					c.getCenter().getYCoordinate(), c.getRadius(), hex(c.getColor()), hex(c.getInnerColor()));
		}
		if (s instanceof Rectangle r) {
			return String.format("Rectangle ul=(%d,%d) w=%d h=%d outline=%s inner=%s",
					r.getUpperLeftPoint().getXCoordinate(), r.getUpperLeftPoint().getYCoordinate(), r.getWidth(),
					r.getHeight(), hex(r.getColor()), hex(r.getInnerColor()));
		}
		if (s instanceof HexagonAdapter h) {
			return String.format("Hexagon x=%d y=%d r=%d border=%s area=%s", h.getX(), h.getY(), h.getRadius(),
					hex(h.getBorderColor()), hex(h.getAreaColor()));
		}
		return s.toString();
	}

	private static Color color(String hex) {
		return Color.decode(hex);
	}

	private static int[] pair(String v) {
		v = v.trim();
		if (v.startsWith("("))
			v = v.substring(1);
		if (v.endsWith(")"))
			v = v.substring(0, v.length() - 1);
		String[] p = v.split(",");
		return new int[] { Integer.parseInt(p[0]), Integer.parseInt(p[1]) };
	}

	private static Map<String, String> keyValues(String desc) {
		Map<String, String> map = new HashMap<>();
		String[] parts = desc.trim().split("\\s+");

		map.put("type", parts[0]);

		for (int i = 1; i < parts.length; i++) {
			String token = parts[i];
			int eq = token.indexOf('=');
			if (eq > 0) {
				String k = token.substring(0, eq);
				String v = token.substring(eq + 1);
				map.put(k, v);
			}
		}
		return map;
	}

	public static Shape parseShape(String desc) {
		Map<String, String> m = keyValues(desc);
		String type = m.get("type");

		switch (type) {
		case "Point": {
			int x = Integer.parseInt(m.get("x"));
			int y = Integer.parseInt(m.get("y"));
			Point p = new Point(x, y);
			p.setColor(color(m.get("color")));
			return p;
		}
		case "Line": {
			int[] s = pair(m.get("start"));
			int[] e = pair(m.get("end"));
			Line l = new Line(new Point(s[0], s[1]), new Point(e[0], e[1]));
			l.setColor(color(m.get("color")));
			return l;
		}
		case "Circle": {
			int[] c = pair(m.get("center"));
			int r = Integer.parseInt(m.get("r"));
			Circle cir = new Circle(new Point(c[0], c[1]), r);
			cir.setColor(color(m.get("outline")));
			cir.setInnerColor(color(m.get("inner")));
			return cir;
		}
		case "Donut": {
			int[] c = pair(m.get("center"));
			int r = Integer.parseInt(m.get("r"));
			int innerR = Integer.parseInt(m.get("innerR"));
			Donut d = new Donut(new Point(c[0], c[1]), r, innerR);
			d.setColor(color(m.get("outline")));
			d.setInnerColor(color(m.get("inner")));
			return d;
		}
		case "Rectangle": {
			int[] ul = pair(m.get("ul"));
			int w = Integer.parseInt(m.get("w"));
			int h = Integer.parseInt(m.get("h"));
			Rectangle r = new Rectangle(new Point(ul[0], ul[1]), w, h);
			r.setColor(color(m.get("outline")));
			r.setInnerColor(color(m.get("inner")));
			return r;
		}
		case "Hexagon": {
			int x = Integer.parseInt(m.get("x"));
			int y = Integer.parseInt(m.get("y"));
			int r = Integer.parseInt(m.get("r"));
			HexagonAdapter ha = new HexagonAdapter(x, y, r);
			ha.setBorderColor(color(m.get("border")));
			ha.setAreaColor(color(m.get("area")));
			return ha;
		}
		default:
			throw new IllegalArgumentException("Unknown shape: " + desc);
		}
	}

}
