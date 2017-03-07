package controller;

import javafx.scene.canvas.GraphicsContext;
import model.DrawModel;
import model.Shape;
import view.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class DrawController {
    private DrawModel drawModel;
    private View view;

    public DrawController(DrawModel drawModel, View view){
        this.drawModel = drawModel;
        this.view = view;
    }//DrawController

    public void clearObserversList(){ drawModel.clearObservers(); }

    public List<Shape> getObservers(){ return drawModel.getObservers(); }

    public void addShape(Shape shape, GraphicsContext gc){ drawModel.addShape(shape, gc); }

    public void removeLatestShape(GraphicsContext gc){ drawModel.undo(gc); }

    public void redo(GraphicsContext gc){ drawModel.redo(gc); }

    public void drawFromReload(){ view.drawFromReload(); }

    public void serializeToFile(String filename) throws IOException { drawModel.serializeToFile(filename); }

    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException { drawModel.deSerializeFromFile(filename); }

    public Shape getShape(double x, double y){ return drawModel.getShape(x, y); }

    public void updateShape(Shape shape, GraphicsContext gc, boolean isfilled){ drawModel.updateShape(shape, gc, isfilled); }

    public void deleteShape(Shape shape, GraphicsContext gc){ drawModel.deleteShape(shape, gc); }
}//class
