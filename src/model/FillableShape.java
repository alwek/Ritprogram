package model;

import javafx.scene.paint.Color;

/**
 * Created by dani on 2017-03-01.
 */
public abstract class FillableShape extends Shape{

    private boolean filled;

    protected FillableShape(double x1, double x2, double y1, double y2, boolean filled, Color color, double lineWidth) {
        super(x1, x2, y1, y2,color,lineWidth);
        this.filled = filled;
    }

    public boolean isFilled() { return filled; }

    public void setFilled(boolean filled) { this.filled = filled; }
}
