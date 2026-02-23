package strategy;

import java.io.*;
import java.util.List;
import geometry.Shape;

public class SaveDrawingStrategy implements SaveStrategy {
    private final List<Shape> shapes;

    public SaveDrawingStrategy(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void save(File file) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(shapes);
        }
    }
}