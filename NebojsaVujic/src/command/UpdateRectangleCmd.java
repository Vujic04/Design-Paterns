package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle original= new Rectangle();
	private boolean captured = false;
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle=rectangle;
		this.newState=newState;
	}
	@Override
	public void execute() {
		if(!captured) {
			original=rectangle.clone();
			captured=true;
		}
		rectangle.applyFrom(newState);
	}

	@Override
	public void unexecute() {
		rectangle.applyFrom(original);
	}

}
