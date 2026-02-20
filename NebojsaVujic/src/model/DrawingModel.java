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
	
	public void toFront(Shape s) {
		if(s==null) return;
		int i= shapes.indexOf(s);
		if(i==-1 || i==shapes.size()-1) return;
		shapes.remove(i);
		shapes.add(i+1,s);
	}
	
	public void toBack(Shape s) {
		if(s==null) return;
		int i= shapes.indexOf(s);
		if(i<=0) return;
		shapes.remove(i);
		shapes.add(i-1,s);
	}
	
	public void bringToFront(Shape s) {
		if(s==null) return;
		int i= shapes.indexOf(s);
		if(i==-1 || i==shapes.size()-1) return;
		shapes.remove(i);
		shapes.add(s);
	}
	
	public void bringToBack(Shape s) {
		if(s==null) return;
		int i= shapes.indexOf(s);
		if(i<=0) return;
		shapes.remove(i);
		shapes.add(0,s);
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
