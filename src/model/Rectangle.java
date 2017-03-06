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
        double maxValueX, maxValueY;
        double minValueX, minValueY;

        gc.setLineWidth(super.getLineWidth());
        gc.setStroke(super.getColor());
        if(super.getX1() > super.getX2()){ maxValueX = super.getX1(); minValueX = super.getX2(); }
        else{ maxValueX = super.getX2(); minValueX = super.getX1(); }

        if(super.getY1() > super.getY2()){ maxValueY = super.getY1(); minValueY = super.getY2(); }
        else{ maxValueY = super.getY2(); minValueY = super.getY1(); }

        if(!super.isFilled())
            gc.strokeRect(minValueX, minValueY, maxValueX - minValueX, maxValueY - minValueY);
        else {
            gc.setFill(super.getColor());
            gc.fillRect(minValueX, minValueY, maxValueX - minValueX, maxValueY - minValueY);
        }
    }
}