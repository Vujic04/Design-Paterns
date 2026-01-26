package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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



public class Controller extends MouseAdapter {
	
	public enum Tool {
	    POINT, LINE, CIRCLE, RECTANGLE, DONUT, SELECT, HEXAGON
	}
	
	private final DrawingModel model;
	private final PnlDrawing view;
	private Point startPoint;
	private Point endPoint;
	private Tool tool = Tool.SELECT;
	private Shape selectedShape;
	

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
		return selectedShape;
	}
	
	public void deleteSelected() {
		Shape s = getSelectedShape();
		if(s != null) {
			model.removeShape(s);
			selectedShape=null;
			view.repaint();
		}
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
	        case SELECT -> selectShape(x, y);
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
			Point point = new Point (x,y,false,dialog.getSelectedColor());
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
		dialog.setVisible(true);
		
		if(dialog.isConfirmed()) {
			Point upperLeft = new Point(x,y);
			int height = dialog.getHeight();
			int width = dialog.getWidth();
			Rectangle rect = new Rectangle(upperLeft, width, height, true, dialog.getOutlineColor(), dialog.getInnerColor());
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
		dialog.setVisible(true);
		
		if (dialog.isConfirmed()) {
			Point center = new Point (x,y);
			int innerRadius = dialog.getInner();
			int outerRadius = dialog.getOuter();
			Donut donut = new Donut(center, outerRadius, innerRadius, true, dialog.getOutlineColor(), dialog.getInnerColor());
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
	public void selectShape(int x, int y) {
        boolean shapeFound = false;

        for (int i = model.getShapes().size() - 1; i >= 0; i--) {
            Shape shape = model.getShapes().get(i);

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
	
	public void clearSelection() {
		selectedShape=null;
		for (Shape s: model.getShapes())
			s.setSelected(false);
	}
	
	public boolean modifySelected() {
		Shape item = getSelectedShape();
		if (item == null) {
			return false;
		}
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

        } else if (item instanceof Circle c) {
            CircleDlg dialog = new CircleDlg();
            dialog.modifyCircle(c);;
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof Donut d) {
            DonutDlg dialog = new DonutDlg();
            dialog.modifyDonut(d);
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof Rectangle r) {
            RectangleDlg dialog = new RectangleDlg();
            dialog.modifyRectangle(r);
            if (!dialog.isConfirmed()) return false;

        } else if (item instanceof HexagonAdapter h) {
            HexagonDlg dialog = new HexagonDlg();
            dialog.modifyHexagon(h);
            if (!dialog.isConfirmed()) return false;

        } 
		clearSelection();
		tool=Tool.SELECT;
		view.repaint();
		return true;
	}
	
}
