package model;

/**
 * Created by dani on 2017-03-10.
 */
public class UpdateUndo implements UndoInterface {
    @Override
    public void undo(Shape undoShape, DrawModelInterface drawModel) {
        Shape shape = drawModel.getObservers().remove(drawModel.getObservers().size()-1);
        drawModel.getObservers().add(undoShape);
        drawModel.getUndoStack().push(shape);
    }
}
