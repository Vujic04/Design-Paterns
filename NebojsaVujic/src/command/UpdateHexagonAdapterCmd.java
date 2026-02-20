package command;

import controller.ShapeFormat;
import hexagonAdapter.HexagonAdapter;

public class UpdateHexagonAdapterCmd implements Command {
	private HexagonAdapter hexagon;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	private boolean captured = false;
	
	public UpdateHexagonAdapterCmd(HexagonAdapter hexagon,HexagonAdapter newState) {
		this.hexagon=hexagon;
		this.newState=newState;
	}
	
	
	
	@Override
	public void execute() {
		if(!captured) {
			original=hexagon.clone();
			captured=true;
		}
		hexagon.applyFrom(newState);
	}

	@Override
	public void unexecute() {
		hexagon.applyFrom(original);
	}



	@Override
	public String getLogText() {
		return "MODIFY  " + ShapeFormat.describeShape(original) + " -> " + ShapeFormat.describeShape(newState);
	}


	@Override
	public String getUndoLogText() {
		return "UNDO MODIFY  " + ShapeFormat.describeShape(newState) + " -> " + ShapeFormat.describeShape(original);	}

}
