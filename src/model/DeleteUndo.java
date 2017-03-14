package model;

import java.util.List;
import java.util.Stack;

/**
 * Created by dani on 2017-03-10.
 */
public class DeleteUndo implements UndoInterface {

    @Override
    public void undo(DrawModelInterface drawModelInterface) {
        Stack<Shape> undoStack = drawModelInterface.getUndoStack();
        List<Shape> shapeList = drawModelInterface.getObservers();

        shapeList.add(undoStack.pop());

        drawModelInterface.setUndoStack(undoStack);
        drawModelInterface.setObservers(shapeList);
        drawModelInterface.setDeleteUndo(false);
    }
}
