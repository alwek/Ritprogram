package model;

/**
 * Created by dani on 2017-02-25.
 */
public class ShapeFactoryImpl implements ShapeFactory {

    private Line line;
    private Rectangle rectangle;
    private Circle circle;

    public ShapeFactoryImpl(Line line, Rectangle rectangle, Circle circle){
        this.line = line;
        this.rectangle = rectangle;
        this.circle = circle;
    }

    @Override
    public Line createLine() {
        try{
            return line.clone();
        }catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Rectangle createRectangle() {
        try{
            return rectangle.clone();
        }catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Circle createCircle() {
        try{
            return circle.clone();
        }catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
