package command;

import java.util.List;

import controller.Controller;
import controller.ShapeFormat;
import geometry.Shape;

public class UpdateSelectionCmd implements Command {
	private final  Controller controller;
	private final List<Shape> before;
	private final List<Shape> after;
	private final Shape shape;
    private final boolean nowSelected;
	
	public UpdateSelectionCmd(Controller controller, List<Shape> before, List<Shape> after,  Shape shape, boolean nowSelected) {
        this.controller = controller;
        this.before = before;
        this.after = after;
        this.shape = shape;
        this.nowSelected = nowSelected;
    }
	@Override
	public void execute() {
		controller.applySelection(after);
	}

	@Override
	public void unexecute() {
		controller.applySelection(before);
	}
	
	@Override
	public String getLogText() {
	    if (shape==null) return "CLEAR SELECTION";
	    
	    if (shape != null) {
            String desc = ShapeFormat.describeShape(shape); 
            return (nowSelected ? "SELECT " : "DESELECT ") + desc + " (count=" + after.size() + ")";
        }
	    
	    return "SELECT count " + after.size();
	}
	@Override
	public String getUndoLogText() {
	    if (shape==null) return "UNDO CLEAR SELECTION";
	    if (shape != null) {
            String desc = ShapeFormat.describeShape(shape); 
            return "UNDO " + (nowSelected ? "SELECT " : "DESELECT ") + desc + " (count=" + before.size() + ")";
        }
	    return "UNDO SELECT count " + before.size();
	}
	
}
