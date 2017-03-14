package model;

import java.util.List;
import java.util.Stack;

/**
 * Created by dani on 2017-03-10.
 */
public class UpdateUndoInterface implements UndoInterface {

    @Override
    public void undo(DrawModelInterface drawModelInterface) {
        Stack<Shape> undoStack = drawModelInterface.getUndoStack();
        List<Shape> shapeList = drawModelInterface.getObservers();

        Shape shape = shapeList.remove(shapeList.size()-1);
        shapeList.add(undoStack.pop());
        undoStack.push(shape);

        drawModelInterface.setUndoStack(undoStack);
        drawModelInterface.setObservers(shapeList);
        drawModelInterface.setUpdateUndo(false);
    }
}
