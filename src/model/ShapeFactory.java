package model;

import java.io.Serializable;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public interface ShapeFactory extends Serializable {
    Line createLine();
    Rectangle createRectangle();
    Circle createCircle();
   // void draw(GraphicsContext gc);
}
