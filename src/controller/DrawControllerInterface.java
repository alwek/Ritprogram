package controller;

import javafx.scene.canvas.GraphicsContext;
import model.Shape;

import java.io.IOException;
import java.util.List;

/**
 * Created by dani on 2017-03-09.
 */
public interface DrawControllerInterface {
    void clearObserversList();
    List<Shape> getObservers();
    void addShape(Shape shape, GraphicsContext gc);
    void removeLatestShape(GraphicsContext gc);
    void redo(GraphicsContext gc);
    void drawFromReload();
    void serializeToFile(String filename) throws IOException;
    void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException;
    Shape getShape(double x, double y);
    void updateShape(Shape shape, GraphicsContext gc, boolean isfilled);
    void deleteShape(Shape shape, GraphicsContext gc);
    Shape checkAndCreateShape(Shape shape);
}
