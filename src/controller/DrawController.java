package controller;

import javafx.scene.canvas.GraphicsContext;
import model.DrawModel;
import model.Line;
import model.Shape;
import view.View;

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

    public void setShapeList(List<Shape> shapesList){
        drawModel.setShapeList(shapesList);
    }

    public List<Shape> getShapeList(){
        return drawModel.getShapeList();
    }

    public void addShape(Shape shape){
        drawModel.addShape(shape);
    }

    public Shape getShape(int index){
        return drawModel.getShape(index);
    }

    public void drawShape(Shape shape, GraphicsContext gc){
        drawModel.drawShape(shape, gc);
    }

    public void drawFromReload(){
        view.drawFromReload();
    }
}//class
