package command;


import controller.ShapeFormat;
import geometry.Shape;
import model.DrawingModel;

public class AddShapeCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	
	public AddShapeCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	
	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
	}


	public String getLogText() {
		return "ADD " + ShapeFormat.describeShape(shape);
	}


	public String getUndoLogText() {
		return "UNDO ADD " + ShapeFormat.describeShape(shape);
	}
	
}
