package command;

import java.awt.Color;

import controller.ShapeFormat;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagonAdapter.HexagonAdapter;
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
