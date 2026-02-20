package controller;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Observer.Observable;
import Observer.Observer;
import command.AddShapeCmd;
import command.Command;
import command.RemoveShapeCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonAdapterCmd;
import command.UpdateLineCmd;
import command.UpdateMoveZCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import command.UpdateSelectionCmd;
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
	
	private Color innerColor=Color.GRAY;
	private Color outlineColor=Color.BLACK;
	
	private final java.util.List<Shape> selectedShapes = new java.util.ArrayList<Shape>();
	
	private final List<Observer> observers = new ArrayList<>();
	
	private final java.util.Stack<command.Command>undoStack= new java.util.Stack<>();
	private final java.util.Stack<command.Command>redoStack= new java.util.Stack<>();
	
	public java.util.List<Shape> snapshotSelection() {
	    return new java.util.ArrayList<>(selectedShapes);
	}
	
	public void applySelection(java.util.List<Shape> select) {
		for(Shape s : model.getShapes()) {
			s.setSelected(false);
		}
		selectedShapes.clear();
		
		for(Shape s : select) {
			s.setSelected(true);
			selectedShapes.add(s);
		}
		notifyObservers();
		view.repaint();
	}


	

	public Controller(DrawingModel model, PnlDrawing view) {
		this.model = model;
		this.view = view;
		view.addMouseListener(this);
	}
	
	public void setTool(Tool tool) {
        this.tool = tool;
        System.out.println("SET TOOL -> " + this.tool);
    }
	
	public Shape getSelectedShape() { return selectedShapes.size() == 1 ? selectedShapes.get(0) : null; }
	
	public boolean hasSelection() { return !selectedShapes.isEmpty(); }

	public int getSelectionCount() { return selectedShapes.size(); }
	

	private final java.util.List<String> log = new java.util.ArrayList<>();

	public java.util.List<String> getLog() {
	    return java.util.Collections.unmodifiableList(log);
	}

	private void addLog(String entry) {
	    log.add(entry);
	    notifyObservers();
	}
	
	public void deleteSelected() {
		
		if(selectedShapes.isEmpty()) {
			return;
		}
		
		java.util.List<Shape> delete = new java.util.ArrayList<>(selectedShapes); 
		
		clearSelection();
		
		for(Shape s : delete) {
			executeCommand(new RemoveShapeCmd(model,s));
		}
		notifyObservers();
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
			executeCommand(new AddShapeCmd(model,point));
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
				executeCommand(new AddShapeCmd(model,line));
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
			executeCommand(new AddShapeCmd(model,circle));
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
			executeCommand(new AddShapeCmd(model,rect));
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
			executeCommand(new AddShapeCmd(model,donut));
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
			executeCommand(new AddShapeCmd(model,hexagon));
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
	


	public void selectShapes(int x, int y) {
	    java.util.List<Shape> before = snapshotSelection();
	    boolean nowSelected = false;
	    
	    Shape topMost = findTopMostAt(x, y);
	    java.util.List<Shape> after = new java.util.ArrayList<>(before);

	    if (topMost == null) {
	        after.clear();
	    } else {
	        if (after.contains(topMost)) {
	        	after.remove(topMost);
	        	nowSelected = false;
	        }
	        else {
	        	after.add(topMost);
	        	nowSelected = true;
	        }
	    }

	    executeCommand(new UpdateSelectionCmd(this, before, after, topMost, nowSelected));
	}

	
	public void clearSelection() {
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
		
		if (item instanceof Point p) {
			Point before=p.clone();
		    
			PointDlg dialog = new PointDlg();
			dialog.modifyPoint(p);
			
			if(!dialog.isConfirmed()) {
				p.applyForm(before);
				return false;
			}
			Point after=p.clone();
			p.applyForm(before);
			executeCommand(new UpdatePointCmd(p, after));
			item.setSelected(true);
			
		} else if (item instanceof Line l) {
			Line before = l.clone();
            LineDlg dialog = new LineDlg();
            dialog.modifyLine(l);
            if (!dialog.isConfirmed()) {
            	l.applyFrom(before);
            	return false;
            }
            Line after =l.clone();
            l.applyFrom(before);
            executeCommand(new UpdateLineCmd(l, after));
            item.setSelected(true);

        } else if (item instanceof Donut d) {
        	Donut before =d.clone();
            DonutDlg dialog = new DonutDlg();
            dialog.modifyDonut(d);
            if (!dialog.isConfirmed()) {
            	d.applyFrom(before);
            	return false;
            }
            Donut after=d.clone();
            d.applyFrom(before);
            executeCommand(new UpdateDonutCmd(d, after));
            item.setSelected(true);


        } else if (item instanceof Circle c) {
        	Circle before = c.clone();
            CircleDlg dialog = new CircleDlg();
            dialog.modifyCircle(c);;
            if (!dialog.isConfirmed()) {
            	c.applyFrom(before);
            	return false;
            }
            Circle after=c.clone();
            c.applyFrom(before);
            executeCommand(new UpdateCircleCmd(c, after));
            item.setSelected(true);

        }  else if (item instanceof Rectangle r) {
        	Rectangle before = r.clone();
            RectangleDlg dialog = new RectangleDlg();
            dialog.modifyRectangle(r);
            if (!dialog.isConfirmed()) {
            		r.applyFrom(before);
            		return false;
            	}
            Rectangle after=r.clone();
            r.applyFrom(before);
            executeCommand(new UpdateRectangleCmd(r, after));
            item.setSelected(true);

        } else if (item instanceof HexagonAdapter h) {
        	HexagonAdapter before=h.clone();
            HexagonDlg dialog = new HexagonDlg();
            dialog.modifyHexagon(h);
            if (!dialog.isConfirmed()) { 
            	h.applyFrom(before);
            	return false;
            }
            HexagonAdapter after =h.clone();
            h.applyFrom(before);
            executeCommand(new UpdateHexagonAdapterCmd(h, after));
            item.setSelected(true);
        } 
		tool=Tool.SELECT;
		view.repaint();
		return true;
	}
	private void executeCommand(Command c) {
		c.execute();
		undoStack.push(c);
		redoStack.clear();
		addLog(c.getLogText());
		view.repaint();
		notifyObservers();
	}
	public void undo() {
		if(undoStack.isEmpty()) return;
		Command c = undoStack.pop();
		c.unexecute();
		System.out.println("MODEL size = " + model.getShapes().size());
		redoStack.push(c);
		addLog(c.getUndoLogText());
		view.repaint();
		notifyObservers();
	}
	
	public void redo() {
		if(redoStack.isEmpty()) return;
		Command c = redoStack.pop();
		c.execute();
		System.out.println("MODEL size = " + model.getShapes().size());
		undoStack.push(c);
		addLog("REDO " + c.getLogText());
		view.repaint();
		notifyObservers();
	}
	
	public boolean undoPosible() {
		return !undoStack.isEmpty();
	}
	public boolean redoPosible() {
		return !redoStack.isEmpty();
	}
	
	@Override
	public void addObservers(Observer o) { observers.add(o); }

	@Override
	public void removeObservers(Observer o) { observers.remove(o); }

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	public Color getInnerColor() { return innerColor; }

	public void setInnerColor(Color c) {
		innerColor = c;
		System.out.print(innerColor);
	}

	public Color getOutlineColor() { return outlineColor; }

	public void setOutlineColor(Color c) {
		outlineColor = c;
		System.out.print(outlineColor);
	}
	
	public void toFront() {
		Shape s = getSelectedShape();
		if (s==null) return;
		int i = model.indexOf(s);
		executeCommand(new UpdateMoveZCmd(model, s, i + 1));
	}
	
	public void toBack() {
		Shape s = getSelectedShape();
		if (s==null) return;
		int i = model.indexOf(s);
		executeCommand(new UpdateMoveZCmd(model, s, i - 1));
	}
	
	public void bringToFront() {
		Shape s = getSelectedShape();
		if (s==null) return;
		int i = model.getShapes().size() - 1;
		executeCommand(new UpdateMoveZCmd(model, s, i));
	}
	
	public void bringToBack() {
		Shape s = getSelectedShape();
		if (s==null) return;
		executeCommand(new UpdateMoveZCmd(model, s, 0));
	}
	
}
