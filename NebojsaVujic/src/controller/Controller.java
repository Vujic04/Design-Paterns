package controller;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Observer.Observable;
import Observer.Observer;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagonAdapter.HexagonAdapter;
import model.DrawingModel;
import view.CircleDlg;
import view.DonutDlg;
import view.HexagonDlg;
import view.LineDlg;
import view.PnlDrawing;
import view.PointDlg;
import view.RectangleDlg;



public class Controller extends MouseAdapter implements Observable{
	
	public enum Tool {
	    POINT, LINE, CIRCLE, RECTANGLE, DONUT, SELECT, HEXAGON
	}
	
	private final DrawingModel model;
	private final PnlDrawing view;
	private Point startPoint;
	private Point endPoint;
	private Tool tool = Tool.SELECT;
	private Shape selectedShape;
	
	private Color innerColor=Color.GRAY;
	private Color outlineColor=Color.BLACK;
	
	private final java.util.List<Shape> selectedShapes = new java.util.ArrayList<Shape>();
	
	private final List<Observer> observers = new ArrayList<>();
	



	

	public Controller(DrawingModel model, PnlDrawing view) {
		this.model = model;
		this.view = view;
		view.addMouseListener(this);
	}
	
	public void setTool(Tool tool) {
        this.tool = tool;
        System.out.println("SET TOOL -> " + this.tool);
    }
	
	public Shape getSelectedShape() {
		return selectedShapes.size() == 1 ? selectedShapes.get(0) : null;
	}
	public boolean hasSelection() {
	    return !selectedShapes.isEmpty();
	}

	public int getSelectionCount() {
	    return selectedShapes.size();
	}


	public void deleteSelected() {
		
		if(selectedShapes.isEmpty()) {
			return;
		}
		
		java.util.List<Shape> delete = new java.util.ArrayList<>(selectedShapes); 
		
		clearSelection();
		
		for(Shape s : delete) {
			model.removeShape(s);
		}
		notifyObservers();
		view.repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	    int x = e.getX();
	    int y = e.getY();

	    System.out.println("CLICK tool=" + tool + " x=" + x + " y=" + y);

	    switch (tool) {
	        case POINT -> addPoint(x, y);
	        case LINE -> addLine(x, y);
	        case CIRCLE -> addCircle(x, y);
	        case RECTANGLE -> addRectangle(x, y);
	        case DONUT -> addDonut(x, y);
	        case HEXAGON -> addHexagon(x, y);
	        case SELECT -> selectShapes(x, y);
	    }

	    view.repaint();
	}

	
	public void addPoint (int x, int y) {
		PointDlg dialog = new PointDlg();
		dialog.regularTextFields();
		dialog.setTextFieldForX(x);
		dialog.setTextFieldForY(y);
		dialog.setVisible(true);

		if(dialog.isConfirmed()) {
			Color c=dialog.getSelectedColor();
			if (c == null) c = getOutlineColor();
			Point point = new Point (x,y,false,c);
			model.addShape(point);
			view.repaint();
		}
		
		tool=Tool.SELECT;
	}
	
	public void addLine (int x, int y) {
		if(startPoint==null) {
			startPoint = new Point (x,y);
			return;
		}
		
		if(endPoint==null) {
			endPoint= new Point (x,y);
			LineDlg dialog = new LineDlg();
			dialog.setTextFieldForX1(startPoint.getXCoordinate());
			dialog.setTextFieldForY1(startPoint.getYCoordinate());
			dialog.setTextFieldForX2(endPoint.getXCoordinate());
			dialog.setTextFieldForY2(endPoint.getYCoordinate());
			dialog.regularTextFields();
			dialog.setColor(getOutlineColor());
			dialog.setVisible(true);
			
			if(dialog.isConfirmed()) {
				Line line = new Line (startPoint, endPoint, false, dialog.getSelectedColor());
				model.addShape(line);
				view.repaint();
			}
			startPoint=null;
			endPoint=null;
			tool = Tool.SELECT;
		}
	}
	
	public void addCircle(int x,int y) {
		CircleDlg dialog = new CircleDlg();
		dialog.setTextFieldForX(x);
		dialog.setTextFieldForY(y);
		dialog.regularTextFields();
		dialog.setOutlineColor(getOutlineColor());
		dialog.setInnerColor(getInnerColor());
		dialog.setVisible(true);
		
		if(dialog.isConfirmed()) {
			int radius = dialog.getRadius();
			Point center = new Point (x,y);
			Circle circle = new Circle (center, radius, false, dialog.getOutlineColor(), dialog.getInnerColor());
			model.addShape(circle);
			view.repaint();
		}
		tool = Tool.SELECT;
	}
	
	public void addRectangle(int x, int y) {
		RectangleDlg dialog = new RectangleDlg();
		dialog.setTextFieldForX(x);
		dialog.setTextFieldForY(y);
		dialog.regularTextFields();
		dialog.setOutlineColor(getOutlineColor());
		dialog.setInnerColor(getInnerColor());
		dialog.setVisible(true);
		
		if(dialog.isConfirmed()) {
			Point upperLeft = new Point(x,y);
			int height = dialog.getHeightRect();
			int width = dialog.getWidthRect();
			Rectangle rect = new Rectangle(upperLeft, width, height, false, dialog.getOutlineColor(), dialog.getInnerColor());
			model.addShape(rect);
			view.repaint();
		}
		tool = Tool.SELECT;
	}
	
	public void addDonut(int x, int y) {
		DonutDlg dialog = new DonutDlg();
		dialog.setTextFieldForX(x);
		dialog.setTextFieldForY(y);
		dialog.regularTextFields();
		dialog.setOutlineColor(getOutlineColor());
		dialog.setInnerColor(getInnerColor());
		dialog.setVisible(true);
		
		if (dialog.isConfirmed()) {
			Point center = new Point (x,y);
			int innerRadius = dialog.getInner();
			int outerRadius = dialog.getOuter();
			Donut donut = new Donut(center, outerRadius, innerRadius, false, dialog.getOutlineColor(), dialog.getInnerColor());
			model.addShape(donut);
			view.repaint();
		}
		tool = Tool.SELECT;
	}
	
	public void addHexagon(int x,int y) {
		HexagonDlg dialog = new HexagonDlg();
		dialog.setTextFieldForX(x);
		dialog.setTextFieldForY(y);
		dialog.regularTextFields();
		dialog.setOutlineColor(getOutlineColor());
		dialog.setInnerColor(getInnerColor());
		dialog.setVisible(true);
		
		if (dialog.isConfirmed()) {
			int radius = dialog.getRadius();
			HexagonAdapter hexagon = new HexagonAdapter(x,y,radius,false,dialog.getOutlineColor(),dialog.getInnerColor());
			model.addShape(hexagon);
			view.repaint();
		}
		tool = Tool.SELECT;
		System.out.println(model.getShapes());
	}
	
	
	
	private Shape findTopMostAt(int x,int y) {
		java.util.List<Shape> shapes = model.getShapes();
		
		for (int i=shapes.size() -  1; i>=0;i--) {
			Shape s = shapes.get(i);
			if (s.contains(x, y)) {
				return s;
			}
		}
		return null;
	}
	
	
	public void selectShapes (int x, int y) {
		Shape topMost = findTopMostAt(x,y);
		
		if(topMost==null) {
			clearSelection();
			notifyObservers();
			return;
		}
		
		if(selectedShapes.contains(topMost)) {
			topMost.setSelected(false);
			selectedShapes.remove(topMost);
		}else {
			topMost.setSelected(true);
			selectedShapes.add(topMost);
		}
		notifyObservers();
	}	
	

	
	public void clearSelection() {
		//selectedShape=null;
		for (Shape s: model.getShapes()) {
			s.setSelected(false);
		}
		selectedShapes.clear();
		notifyObservers();
	}
	
	public boolean modifySelected() {
		
		if (selectedShapes.size() != 1) {
			return false;
		}
		Shape item = selectedShapes.get(0);
		boolean changed=false;
		if (item instanceof Point p) {
			PointDlg dialog = new PointDlg();
			dialog.modifyPoint(p);
			if(!dialog.isConfirmed()) {
				return false;
			}
		} else if (item instanceof Line l) {
            LineDlg dialog = new LineDlg();
            dialog.modifyLine(l);
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof Donut d) {
            DonutDlg dialog = new DonutDlg();
            dialog.modifyDonut(d);
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof Circle c) {
            CircleDlg dialog = new CircleDlg();
            dialog.modifyCircle(c);;
            if (!dialog.isConfirmed()) return false;

        }  else if (item instanceof Rectangle r) {
            RectangleDlg dialog = new RectangleDlg();
            dialog.modifyRectangle(r);
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof HexagonAdapter h) {
            HexagonDlg dialog = new HexagonDlg();
            dialog.modifyHexagon(h);
            if (!dialog.isConfirmed()) return false;

        } 
		if(!changed) return false;
		clearSelection();
		tool=Tool.SELECT;
		view.repaint();
		return true;
	}

	@Override
	public void addObservers(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObservers(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : observers) {
			o.update();
		}
		
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color c) {
		innerColor = c;
		System.out.print(innerColor);
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color c) {
		outlineColor = c;
		System.out.print(outlineColor);
	}
	
}
