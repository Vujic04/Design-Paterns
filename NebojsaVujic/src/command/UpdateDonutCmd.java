package command;

import controller.ShapeFormat;
import geometry.Donut;

public class UpdateDonutCmd implements Command {
	private Donut donut;
	private Donut newState;
	private Donut original= new Donut();
	private boolean captured = false;
	
	
	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut=donut;
		this.newState=newState;
	}
	@Override
	public void execute() {
		if(!captured) {
			original=donut.clone();
			captured=false;
		}
		donut.applyFrom(newState);
	}

	@Override
	public void unexecute() {
		donut.applyFrom(original);
	}
	@Override
	public String getLogText() {
		return "MODIFY  " + ShapeFormat.describeShape(original) + " -> " + ShapeFormat.describeShape(newState);
	}
	@Override
	public String getUndoLogText() {
		return "MODIFY  " + ShapeFormat.describeShape(newState) + " -> " + ShapeFormat.describeShape(original);
	}

}
