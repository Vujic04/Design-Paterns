package view;


import javax.swing.JPanel;
import java.awt.Graphics;
import geometry.Shape;
import model.DrawingModel;


public class PnlDrawing extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	private final DrawingModel model;
	
	
	public PnlDrawing(DrawingModel model) {
        this.model = model;
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape shape : model.getShapes()) {
			shape.draw(g);
		}
		System.out.println("PAINT shapes=" + model.getShapes().size());
	}
	
	
}
