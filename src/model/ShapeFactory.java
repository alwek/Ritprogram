package model;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public interface ShapeFactory{
    Line createLine();
    Rectangle createRectangle();
    Circle createCircle();
    Polygon createPolygon();
}
