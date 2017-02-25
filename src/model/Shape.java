package model;

import javafx.scene.canvas.GraphicsContext;
import java.io.Serializable;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public interface Shape extends Serializable {
    void draw(GraphicsContext gc);
}
