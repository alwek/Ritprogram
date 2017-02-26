package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by dani on 2017-02-26.
 */
public interface DrawObserver {
    void update(GraphicsContext gc);
}
