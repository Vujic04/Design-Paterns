package command;

import controller.ShapeFormat;
import geometry.Point;

public class UpdatePointCmd implements Command{
	private Point point;
	private Point newState;
	private Point original = new Point();
	private boolean captured=false;
	
	public UpdatePointCmd(Point point, Point newState) {
		this.point=point;
		this.newState=newState;
	}

	@Override
	public void execute() {
		if(!captured) {
		original=point.clone();
		captured=true;
		}
		point.applyForm(newState);
	}

	@Override
	public void unexecute() {
		point.applyForm(original);
	}

	@Override
	public String getLogText() {
		return "MODIFY  " + ShapeFormat.describeShape(original) + " -> " + ShapeFormat.describeShape(newState);
	}

	@Override
	public String getUndoLogText() {
		return "UNDO MODIFY  " + ShapeFormat.describeShape(newState) + " -> " + ShapeFormat.describeShape(original);
	}
	
}
