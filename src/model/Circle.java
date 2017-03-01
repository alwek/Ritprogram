package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Circle extends Shape{

    private double radius;

    public Circle(double x1, double x2, double y1, double y2, double radius){
        super(x1,x2,y1,y2);
        this.radius = radius;
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Circle clone() throws CloneNotSupportedException {
        return new Circle(super.getX1(),super.getX2(),super.getY1(),super.getY2(), radius);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.moveTo(super.getX1(), super.getY1());
        gc.lineTo(super.getX2(), super.getY2());
        gc.stroke();
    }

    public double getRadius() { return radius; }

    public void setRadius(double radius) { this.radius = radius; }
}
