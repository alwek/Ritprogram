package model;

/**
 * Created by dani on 2017-03-16.
 */
public class Undo implements UndoInterface {

    @Override
    public void undo(Shape undoShape, DrawModelInterface drawModel) { drawModel.getUndoStack().push(undoShape); }
}
