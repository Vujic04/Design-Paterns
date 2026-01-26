package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	
	private final List<Shape> shapes = new ArrayList<Shape>();
	private Shape selectedShape;
	
	public List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }
	
	public void addShape(Shape s) {
		shapes.add(s);
	}
	
	public void removeShape(Shape s) {
		if (s == null) return;
		shapes.remove(s);
		if(selectedShape==s) 
			selectedShape=null;
	}
	
}
