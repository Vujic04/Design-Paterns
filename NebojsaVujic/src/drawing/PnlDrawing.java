package drawing;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;
import geometry.Line;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Donut;

public class PnlDrawing extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private String selected = null;
	private Point startPoint;
	private Point endPoint;
	private Shape selectedShape;
	/**
	 * Create the panel.
	 */
		public PnlDrawing() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (selected==null) {
						JOptionPane.showMessageDialog(null, "Please select a shape", "Message", JOptionPane.INFORMATION_MESSAGE);
						return;
					}   
					switch (selected) {
					case "Point":{
						addPoint(e.getX(),e.getY());
					}break;
					case "Line":{
						addLine(e.getX(),e.getY());
					}break;
					case "Circle":{
						addCircle(e.getX(),e.getY());
					}break;
					case "Rectangle":{
						addRectangle(e.getX(),e.getY());
					}break;
					case "Donut":{
						addDonut(e.getX(),e.getY());
					}break;
					case "Selected":{
						selectingShape(e.getX(),e.getY());
					}
					}
				}
			});
		}
		
		private void addPoint (int x, int y) {
			PointDlg dialog = new PointDlg();
			dialog.regularTextFields();
			dialog.setTextFieldForX(x);
			dialog.setTextFieldForY(y);
			dialog.setVisible(true);
			
			if(dialog.isConfirmed()) {
				Point point= new Point(x,y,false,dialog.getSelectedColor());
				shapes.add(point);
				setSelected("Selected");
				repaint();
			}
			setSelected("Selected");
		}
		
		private void addLine (int x,int y) {
			LineDlg dialog = new LineDlg();
			if (startPoint==null) {
				startPoint=new Point(x,y);
			}else if(endPoint==null ) {
				endPoint=new Point(x,y);
				dialog.setTextFieldForX1(startPoint.getXCoordinate());
				dialog.setTextFieldForY1(startPoint.getYCoordinate());
				dialog.setTextFieldForX2(endPoint.getXCoordinate());
				dialog.setTextFieldForY2(endPoint.getYCoordinate());
				dialog.regularTextFields();
				
				dialog.setVisible(true);
				if (dialog.isConfirmed()) {
					Line line= new Line (startPoint,endPoint,false,dialog.getSelectedColor());
					shapes.add(line);
					setSelected("Selected");
					repaint();
				}
				startPoint=null;
				endPoint=null;
				setSelected("Selected");
			}
		}
		
		private void addCircle(int x, int y) {
	        CircleDlg dialog = new CircleDlg();
	        dialog.setTextFieldForX(x);
	        dialog.setTextFieldForY(y);
	        dialog.regularTextFields();
	        dialog.setVisible(true);

	        if (dialog.isConfirmed()) {
	            int radius = dialog.getRadius();
	            Point center = new Point(x, y);
	            Circle circle = new Circle(center, radius, true,dialog.getOutlineColor(),dialog.getInnerColor());
	            shapes.add(circle);
	            setSelected("Selected");
	            repaint();
	        }
	        setSelected("Selected");
	    }
		
		private void addRectangle(int x,int y) {
			RectangleDlg dialog=new RectangleDlg();
			Rectangle rect=new Rectangle();
			dialog.setTextFieldForX(x);
			dialog.setTextFieldForY(y);
			dialog.regularTextFields();
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				Point upperLeft=new Point(x,y);
				int height= dialog.getHeightRect();
				int width=dialog.getWidthRect();
				rect=new Rectangle(upperLeft,width,height,true,dialog.getOutlineColor(),dialog.getInnerColor());
				shapes.add(rect);
				setSelected("Selected");
				repaint();
			}
			setSelected("Selected");
		}
		
		private void addDonut(int x,int y) {
			DonutDlg dialog=new DonutDlg();
			Donut donut=new Donut();
			dialog.setTextFieldForX(x);
			dialog.setTextFieldForY(y);
			dialog.regularTextFields();
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				Point center= new Point(x,y);
				int innerRad=dialog.getInner();
				int outerRad=dialog.getOuter();
				donut=new Donut(center,outerRad,innerRad,true,dialog.getOutlineColor(),dialog.getInnerColor());
				shapes.add(donut);
				setSelected("Selected");
				repaint();
			}
			setSelected("Selected");
		}
		
		
		
		private void selectingShape(int x, int y) {
			boolean shapeFound = false;
	        for (int i = shapes.size() - 1; i >= 0; i--) {
	            Shape shape = shapes.get(i);
	            if(shapeFound)
	            	shape.setSelected(false);
	            
	            if (shape.contains(x, y)) {
	                if (selectedShape == shape) {
	                    selectedShape = null;
	                    shape.setSelected(false);
	                } else {
	                	
	                    selectedShape = shape;
	                    shape.setSelected(true);
	                }
	                shapeFound = true;
	            }else {
	            	shape.setSelected(false);
	            }
	            
	        }
	        if (!shapeFound) {
	            selectedShape = null;
	        }
	        repaint();
	    }
		
		
		public void setSelected (String shape) {
			this.selected=shape;
		}
		
		public void paint(Graphics g) {
			super.paintComponent(g);
			for (Shape shape:shapes) {
				shape.draw(g);
			}
		}
		
		public ArrayList<Shape> getShapes()
		{
			return this.shapes;
		}

		public Shape getSelectedShape() {
			// TODO Auto-generated method stub
			return selectedShape;
		}
}
