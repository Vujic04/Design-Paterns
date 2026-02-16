package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command {
	private Circle circle;
	private Circle newState;
	private Circle original= new Circle();
	private boolean captured = false;
	
	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle=circle;
		this.newState=newState;
	}
	
	
	@Override
	public void execute() {
		if(!captured) {
			original=circle.clone();
			captured=true;
		}
		circle.applyFrom(newState);
	}

	@Override
	public void unexecute() {
		circle.applyFrom(original);
	}

}
