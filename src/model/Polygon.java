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
        xPoints = new double[4];
        yPoints = new double[4];

        xPoints[0]= x1;
        xPoints[1]=x2;
        xPoints[2]=x1+(x1/2);
        xPoints[3]=x2+(x2/2);

        yPoints[0]=y1;
        yPoints[1]=y2;
        yPoints[2]=y1+(y1/2);
        yPoints[3]=y2+(y2/2);
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
        gc.strokePolygon(xPoints,yPoints,4);
        System.out.println("Drawn polygon!");
    }
}
