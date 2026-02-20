package command;

import controller.ShapeFormat;
import geometry.Shape;
import model.DrawingModel;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Shape shape;
	
	public RemoveShapeCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	

	@Override
	public void execute() {
		model.removeShape(shape);
	}

	@Override
	public void unexecute() {
		model.addShape(shape);
	}



	@Override
	public String getLogText() {
		return "DELETE " + ShapeFormat.describeShape(shape);
	}



	@Override
	public String getUndoLogText() {
		return "UNDO DELETE " + ShapeFormat.describeShape(shape);
	}

}
