package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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

    public void removeLatestShape(GraphicsContext gc){
        drawModel.undo(gc);
    }

    public void redo(GraphicsContext gc){ drawModel.redo(gc); }

    public void drawFromReload(){
        view.drawFromReload();
    }

    public void serializeToFile(String filename) throws IOException { drawModel.serializeToFile(filename); }

    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException { drawModel.deSerializeFromFile(filename); }

    public void getShape(double x, double y, double lineWidth, boolean fillOption, Color colorOption, GraphicsContext gc){
        drawModel.getShape(x, y, lineWidth, fillOption, colorOption, gc);
    }
}//class
