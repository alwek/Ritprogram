package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by dani on 2017-03-01.
 */
public abstract class Shape extends Prototype implements DrawObserver, Serializable{

    private double x1,x2,y1,y2;
    private double width, height;
    // serialize color
    transient private Color color;
    private double lineWidth;

    protected Shape(double x1, double x2, double y1, double y2, Color color, double lineWidth){
        this.x1=x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.lineWidth = lineWidth;
    }

    @Override
    public abstract Shape clone() throws CloneNotSupportedException;

    public abstract void draw(GraphicsContext gc);

    // serialize color object --> källa: stackOverflow
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeDouble(color.getRed());
        s.writeDouble(color.getGreen());
        s.writeDouble(color.getBlue());
        s.writeDouble(color.getOpacity());
    }

    // serialize color object --> källa: stackOverflow
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        double red = s.readDouble();
        double green = s.readDouble();
        double blue = s.readDouble();
        double opacity = s.readDouble();
        color = Color.color(red, green, blue, opacity);
    }

    public double getX1() { return x1; }

    public void setX1(double x1) { this.x1 = x1; }

    public double getX2() { return x2; }

    public void setX2(double x2) { this.x2 = x2; }

    public double getY1() { return y1; }

    public void setY1(double y1) { this.y1 = y1; }

    public double getY2() { return y2; }

    public void setY2(double y2) { this.y2 = y2; }

    public double getWidth() { return width; }

    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }

    public void setHeight(double height) { this.height = height; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public double getLineWidth() { return lineWidth; }

    public void setLineWidth(double lineWidth) { this.lineWidth = lineWidth; }
}
