package command;

import java.util.List;

import controller.Controller;
import geometry.Shape;

public class UpdateSelectionCmd implements Command {
	private final  Controller controller;
	private final List<Shape> before;
	private final List<Shape> after;
	
	
	public UpdateSelectionCmd(Controller controller, List<Shape> before, List<Shape> after) {
        this.controller = controller;
        this.before = before;
        this.after = after;
    }
	@Override
	public void execute() {
		controller.applySelection(after);
	}

	@Override
	public void unexecute() {
		controller.applySelection(before);
	}

}
