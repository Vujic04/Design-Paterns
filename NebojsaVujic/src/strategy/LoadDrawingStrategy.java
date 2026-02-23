package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import geometry.Shape;
import model.DrawingModel;

public class LoadDrawingStrategy implements LoadStrategy {
	private final DrawingModel model;
	
	
	public LoadDrawingStrategy(DrawingModel model) {
		this.model=model;
	}
	
	@Override
	public void load(File file) throws Exception {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			@SuppressWarnings("unchecked")
			List<Shape> shapes = (List<Shape>) ois.readObject();
			
			model.clearAll();
			for(Shape s : shapes)model.addShape(s);
			
		}
	}

}