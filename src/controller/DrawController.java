package controller;

import javafx.scene.canvas.GraphicsContext;
import model.DrawModel;
import model.Shape;
import model.ShapeFactory;
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

    public void addShape(ShapeFactory shape, GraphicsContext gc, String selectedShape){ drawModel.addShape(shape, gc, selectedShape); }

    public void removeLatestShape(GraphicsContext gc){
        drawModel.undo(gc);
    }

    public void redo(GraphicsContext gc){ drawModel.redo(gc); }

    public void drawFromReload(){
        view.drawFromReload();
    }

    public void serializeToFile(String filename) throws IOException { drawModel.serializeToFile(filename); }

    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException { drawModel.deSerializeFromFile(filename); }
}//class
