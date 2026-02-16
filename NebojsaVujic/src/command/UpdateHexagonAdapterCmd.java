package command;

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

}
