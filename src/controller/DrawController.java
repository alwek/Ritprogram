package controller;

import javafx.scene.canvas.GraphicsContext;
import model.DrawModel;
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

    public void clearShapeList(){ drawModel.clearShapeList(); }

    public List<ShapeFactory> getShapeList(){
        return drawModel.getShapeList();
    }

    public void addShape(ShapeFactory shape){
        drawModel.addShape(shape);
    }

    public void drawShape(ShapeFactory shape, GraphicsContext gc){
        drawModel.drawShape(shape, gc);
    }

    public void drawFromReload(){
        view.drawFromReload();
    }

    public void serializeToFile(String filename) throws IOException { drawModel.serializeToFile(filename); }

    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException { drawModel.deSerializeFromFile(filename); }
}//class
