package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */

public class Rectangle extends FillableShape{

    public Rectangle(double x1, double x2, double y1, double y2, boolean filled) {
        super(x1, x2, y1, y2, filled);
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return new Rectangle(super.getX1(), super.getX2(), super.getY1(),super.getY2(), super.isFilled());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.rect(super.getX1(), super.getY1(), super.getX2()-super.getX1(),super.getY2()-super.getY1());
        gc.stroke();
    }
}