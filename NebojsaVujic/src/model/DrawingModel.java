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
	
	public java.util.List<Shape> getShapesCopy() {
	    return new java.util.ArrayList<>(shapes);
	}
	
	public void addShape(Shape s) {
		shapes.add(s);
	}
	
	public void clearAll() {
		shapes.clear();
		selectedShape=null;
	}
	
	public void removeShape(Shape s) {
		if (s == null) return;
		shapes.remove(s);
		if(selectedShape==s) 
			selectedShape=null;
	}

	public int indexOf(Shape s) { return shapes.indexOf(s); }

	public void moveToIndex(Shape s, int index) {
	    int i = shapes.indexOf(s);
	    if (i == -1) return;
	    if (index < 0) index = 0;
	    if (index > shapes.size() - 1) index = shapes.size() - 1;
	    if (i == index) return;

	    shapes.remove(i);
	    shapes.add(index, s);
	}
}
