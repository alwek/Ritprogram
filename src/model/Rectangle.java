package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public abstract class Rectangle extends Prototype implements DrawObserver{

    private double x1,x2,y1,y2;

    protected Rectangle(double x1, double x2, double y1, double y2){
        this.x1=x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public abstract Rectangle clone() throws CloneNotSupportedException;

    public abstract void draw(GraphicsContext gc);

    public double getX1() { return x1; }

    public void setX1(double x1) { this.x1 = x1; }

    public double getX2() { return x2; }

    public void setX2(double x2) { this.x2 = x2; }

    public double getY1() { return y1; }

    public void setY1(double y1) { this.y1 = y1; }

    public double getY2() { return y2; }

    public void setY2(double y2) { this.y2 = y2; }

    /*    private double x1, x2, y1, y2;

    public Rectangle(double x1, double x2, double y1, double y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }//rectangle

    @Override
    public void draw(GraphicsContext gc) {
        gc.beginPath();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.rect(x1, y1, x2-x1, y2-y1);
        gc.stroke();
    }//draw
    */
}//class
