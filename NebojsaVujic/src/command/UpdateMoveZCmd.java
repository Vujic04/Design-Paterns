package command;

import controller.ShapeFormat;
import geometry.Shape;
import model.DrawingModel;

public class UpdateMoveZCmd implements Command {
		private final DrawingModel model;
	    private final Shape shape;
	    private final int newIndex;
	    private int oldIndex;
	    private boolean captured = false;
	
	    public UpdateMoveZCmd(DrawingModel model, Shape shape, int newIndex) {
	        this.model = model;
	        this.shape = shape;
	        this.newIndex = newIndex;
	    }
	
	    @Override
	    public void execute() {
	    	if (!captured) {
	            oldIndex = model.indexOf(shape);
	            captured = true;
	        }
	        model.moveToIndex(shape, newIndex);
	    }
	
	    @Override
	    public void unexecute() {
	        model.moveToIndex(shape, oldIndex);
	    }

		@Override
		public String getLogText() {
		    return "MOVE Z " + ShapeFormat.describeShape(shape) + " toIndex=" + oldIndex;
		}

		@Override
		public String getUndoLogText() {
			return "UNDO MOVE Z " + ShapeFormat.describeShape(shape) + " toIndex=" + newIndex;
		}
}
