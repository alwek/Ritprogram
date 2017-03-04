package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */

public class Rectangle extends FillableShape{

    public Rectangle(double x1, double x2, double y1, double y2, boolean filled, Color color, double lineWidth) { super(x1, x2, y1, y2, filled,color,lineWidth); }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return new Rectangle(super.getX1(), super.getX2(), super.getY1(),super.getY2(), super.isFilled(),super.getColor(), super.getLineWidth());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.setLineWidth(super.getLineWidth());
        gc.setStroke(super.getColor());
        gc.setFill(super.getColor());
        gc.rect(super.getX1(), super.getY1(), super.getX2()-super.getX1(),super.getY2()-super.getY1());
        gc.stroke();
    }
}