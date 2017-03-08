package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by dani on 2017-02-26.
 * Inspired by https://github.com/iluwatar/java-design-patterns%E2%80%8B
 */
public interface DrawObserver {
    void update(GraphicsContext gc);
}
