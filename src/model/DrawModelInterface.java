package model;

import controller.DrawControllerInterface;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by dani on 2017-03-09.
 */
public interface DrawModelInterface {
    void serializeToFile(String filename) throws IOException;
    void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException;
    List<Shape> getObservers();
    void clearObservers();
    void addShape(Shape shape, GraphicsContext gc);
    void undo(GraphicsContext gc);
    void redo(GraphicsContext gc);
    void setController(DrawControllerInterface drawController);
    Shape getShape(double x, double y);
    void updateShape(Shape shape, GraphicsContext gc, boolean isfilled);
    void deleteShape(Shape shape, GraphicsContext gc);
    Shape checkAndCreateShape(Shape shape);
}
