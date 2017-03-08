package model;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 * Inspired by https://github.com/iluwatar/java-design-patterns%E2%80%8B
 */
public interface ShapeFactory{
    Line createLine();
    Rectangle createRectangle();
    Circle createCircle();
    Polygon createPolygon();
}
