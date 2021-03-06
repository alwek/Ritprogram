package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Circle extends FillableShape{

    private double diameter;

    public Circle(double x1, double x2, double y1, double y2, double diameter, boolean filled, Color color, double lineWidth){
        super(x1,x2,y1,y2, filled, color, lineWidth);
        this.diameter = diameter;
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Circle clone() throws CloneNotSupportedException {
        return new Circle(super.getX1(),super.getX2(),super.getY1(),super.getY2(), diameter, super.isFilled(), super.getColor(),super.getLineWidth());
    }

    @Override
    public void drawShape(GraphicsContext gc) {
        double maxValueX, maxValueY;
        double minValueX, minValueY;

        if(super.getX1() > super.getX2()){
            maxValueX = super.getX1();
            minValueX = super.getX2();
        }else{
            maxValueX = super.getX2();
            minValueX = super.getX1();
        }

        if(super.getY1() > super.getY2()){
            maxValueY = super.getY1();
            minValueY = super.getY2();
        }else{
            maxValueY = super.getY2();
            minValueY = super.getY1();
        }

        System.out.println("Circle draw with color and linewidth: "+(super.getColor()!=null? super.getColor().toString() : null) + " width: "+super.getLineWidth());
        if(!super.isFilled())
            gc.strokeOval(minValueX, minValueY, maxValueX - minValueX, maxValueY - minValueY);
        else
            gc.fillOval(minValueX, minValueY, maxValueX - minValueX, maxValueY - minValueY);

    }

    public double getDiameter() { return diameter; }

    public void setDiameter(double radius) { this.diameter = radius; }
}
