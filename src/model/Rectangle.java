package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Rectangle implements Shape{
    private double x1, x2, y1, y2;

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
}//class
