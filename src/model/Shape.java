package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by dani on 2017-03-01.
 */
public abstract class Shape extends Prototype implements DrawObserver, Serializable{

    private double x1,x2,y1,y2;
    private Color color;

    protected Shape(double x1, double x2, double y1, double y2){
        this.x1=x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public abstract Shape clone() throws CloneNotSupportedException;

    public abstract void draw(GraphicsContext gc);

    public double getX1() { return x1; }

    public void setX1(double x1) { this.x1 = x1; }

    public double getX2() { return x2; }

    public void setX2(double x2) { this.x2 = x2; }

    public double getY1() { return y1; }

    public void setY1(double y1) { this.y1 = y1; }

    public double getY2() { return y2; }

    public void setY2(double y2) { this.y2 = y2; }
}
