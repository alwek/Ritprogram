package model;

/**
 * Created by dani on 2017-03-01.
 */
abstract public class FillableShape extends Shape{

    private boolean filled;

    protected FillableShape(double x1, double x2, double y1, double y2, boolean filled) {
        super(x1, x2, y1, y2);
        this.filled = filled;
    }

    public boolean isFilled() { return filled; }

    public void setFilled(boolean filled) { this.filled = filled; }
}
