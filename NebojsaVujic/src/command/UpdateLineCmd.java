package command;

import geometry.Line;

public class UpdateLineCmd implements Command {
	private Line line;
	private Line newState;
	private Line original= new Line();
	private boolean captured = false;
	
	public UpdateLineCmd(Line line, Line newState) {
		this.line=line;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		if(!captured) {
			original=line.clone();
			captured=true;
		}
		line.applyFrom(newState);
	}

	@Override
	public void unexecute() {
		line.applyFrom(original);
	}

	@Override
	public String getLogText() {
		return "UPDATE LINE " + original.toString() + " -> " + newState.toString();
	}

	@Override
	public String getUndoLogText() {
		return "UNDO UPDATE LINE " + newState.toString() + " -> " + original.toString();
	}

}
