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
	
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	public void clearSelection() {
		selectedShape=null;
		for (Shape s: shapes)
			s.setSelected(false);
	}
	
	
	public void selectShape(int x, int y) {
        boolean shapeFound = false;

        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);

            if (shapeFound) {
                shape.setSelected(false);
                continue;
            }

            if (shape.contains(x, y)) {
                if (selectedShape == shape) {
                    selectedShape = null;
                    shape.setSelected(false);
                } else {
                    selectedShape = shape;
                    shape.setSelected(true);
                }
                shapeFound = true;
            } else {
                shape.setSelected(false);
            }
        }

        if (!shapeFound) selectedShape = null;
    }
	
}
