package model;

/**
 * Created by dani on 2017-02-25.
 */
public class ShapeFactoryImpl implements ShapeFactory {

    private Line line;
    private Rectangle rectangle;
    private Circle circle;
    private Polygon polygon;

    public ShapeFactoryImpl(Line line, Rectangle rectangle, Circle circle, Polygon polygon) {
        this.line = line;
        this.rectangle = rectangle;
        this.circle = circle;
        this.polygon = polygon;
    }

    @Override
    public Line createLine() {
        try {
            return line.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Rectangle createRectangle() {
        try {
            return rectangle.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Circle createCircle() {
        try {
            return circle.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Polygon createPolygon() {
        try {
            return polygon.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
