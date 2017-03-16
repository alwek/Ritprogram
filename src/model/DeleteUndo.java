package model;

/**
 * Created by dani on 2017-03-10.
 */
public class DeleteUndo implements UndoInterface {

    @Override
    public void undo(Shape undoShape, DrawModelInterface drawModel) { drawModel.getObservers().add(undoShape); }
}
