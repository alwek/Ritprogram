package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by dani on 2017-02-25.
 */
public class StraightRectangle extends Rectangle {


    public StraightRectangle(double x1, double x2, double y1, double y2){
        super(x1,x2,y1,y2);
    }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return new StraightRectangle(super.getX1(), super.getX2(), super.getY1(),super.getY2());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.rect(super.getX1(), super.getY1(), super.getX2()-super.getX1(),super.getY2()-super.getY1());
        gc.stroke();
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }
}
