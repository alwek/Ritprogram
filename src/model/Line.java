package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */

public class Line extends Shape{

    public Line(double x1, double x2, double y1, double y2, Color color) {
        super(x1, x2, y1, y2, color);
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Line clone() throws CloneNotSupportedException {
        return new Line(super.getX1(), super.getX2(), super.getY1(), super.getY2(), super.getColor());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.moveTo(super.getX1(), super.getY1());
        gc.lineTo(super.getX2(), super.getY2());
        gc.stroke();
    }
}
