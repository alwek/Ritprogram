package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by dani on 2017-03-05.
 */
public class Polygon extends FillableShape{

    private double[] xPoints, yPoints;

    public Polygon(double x1, double x2, double y1, double y2, boolean filled, Color color, double lineWidth) {
        super(x1, x2, y1, y2, filled, color, lineWidth);
        xPoints = new double[5];
        yPoints = new double[5];

        double xMargin = (x2-x1)/4;
        double yMargin = (y2-y1);

        xPoints[0]=x1;
        xPoints[1]=x1+xMargin*2;
        xPoints[2]=x1+xMargin*4;
        xPoints[3]=x1+xMargin*3;
        xPoints[4]=x1+xMargin;

        yPoints[0]=y1;
        yPoints[1]=y1-yMargin;
        yPoints[2]=y1;
        yPoints[3]=y1+yMargin;
        yPoints[4]=y1+yMargin;
    }

    @Override
    public void update(GraphicsContext gc) { draw(gc); }

    @Override
    public Polygon clone() throws CloneNotSupportedException {
        return new Polygon(super.getX1(), super.getX2(), super.getY1(), super.getY2(), super.isFilled(), super.getColor(), super.getLineWidth());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(super.getLineWidth());
        gc.setStroke(super.getColor());
        if(!super.isFilled())
            gc.strokePolygon(xPoints,yPoints,5);
        else{
            gc.setFill(super.getColor());
            gc.fillPolygon(xPoints, yPoints,5);
        }
        System.out.println("Drawn polygon!");
    }
}
