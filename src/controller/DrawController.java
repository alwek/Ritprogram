package controller;

import javafx.scene.canvas.GraphicsContext;
import model.DrawModelInterface;
import model.Shape;
import view.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class DrawController implements DrawControllerInterface{
    private DrawModelInterface drawModel;
    private View view;

    public DrawController(DrawModelInterface drawModel, View view){
        this.drawModel = drawModel;
        this.view = view;
    }//DrawController

    @Override
    public void clearObserversList(){ drawModel.clearObservers(); }

    @Override
    public List<Shape> getObservers(){ return drawModel.getObservers(); }

    @Override
    public void addShape(Shape shape, GraphicsContext gc){ drawModel.addShape(shape, gc); }

    @Override
    public void removeLatestShape(GraphicsContext gc){ drawModel.undo(gc); }

    @Override
    public void redo(GraphicsContext gc){ drawModel.redo(gc); }

    @Override
    public void drawFromReload(){ view.drawFromReload(); }

    @Override
    public void serializeToFile(String filename) throws IOException { drawModel.serializeToFile(filename); }

    @Override
    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException { drawModel.deSerializeFromFile(filename); }

    @Override
    public Shape getShape(double x, double y){ return drawModel.getShape(x, y); }

    @Override
    public void updateShape(Shape shape, GraphicsContext gc, boolean isfilled){ drawModel.updateShape(shape, gc, isfilled); }

    @Override
    public void deleteShape(Shape shape, GraphicsContext gc){ drawModel.deleteShape(shape, gc); }

    @Override
    public Shape checkAndCreateShape(Shape shape) { return drawModel.checkAndCreateShape(shape); }
}//class
