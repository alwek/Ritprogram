package model;

import controller.DrawController;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by dani on 2017-02-25.
 */
public class DrawModel {
    private List<Shape> observers;
    private DrawController drawController;
    private Stack<Shape> undoStack;
    private boolean updateUndo, deleteUndo;

    public DrawModel(){
        observers = new ArrayList<>();
        undoStack = new Stack<>();
        updateUndo = false;
        deleteUndo=false;
    }//DrawModel

    /**
     * saves all the information to the file path
     * @param filename
     * @throws IOException
     */
    public void serializeToFile(String filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            if(out != null) {
                out.writeObject(observers);
            }
        }//try
        finally {
            try {
                if(out != null)	out.close();
            }//try
            catch(Exception e) {
                e.printStackTrace();
            }//catch
        }//finally
    }//serializeToFile

    /**
     * load all the information from the file path
     * @param filename
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        ArrayList<Shape> readObject = new ArrayList<>();
        observers.clear();
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            readObject = (ArrayList<Shape>) in.readObject();
            for(Shape observer : readObject){
                observers.add(observer);
            }
        }//try
        finally {
            try{
                if(in != null)
                    in.close();
            }//try
            catch(Exception e){
                System.out.println("serialze says: " +e.getMessage());
            }//catch
        }//finally
        drawController.drawFromReload();
    }//deSerializeFromFile

    public List<Shape> getObservers(){
        return observers;
    }

    public void clearObservers(){ observers.clear(); }

    public void addShape(Shape shape, GraphicsContext gc){
        observers.add(shape);
        notifyObservers(gc);
        undoStack.clear();
        updateUndo = false;
        deleteUndo=false;
    }//addShape

    public void undo(GraphicsContext gc){
        if(deleteUndo){
            System.out.println("Special case delete");
            deleteUndo = !deleteUndo;
            observers.add(undoStack.pop());
        }else if(updateUndo){
            System.out.println("Special case update");
            Shape shape = undoStack.pop();
            observers.remove(observers.size()-1);
            observers.add(shape);
            updateUndo = !updateUndo;
        }else{
            if(observers.size() > 0) {
                undoStack.push(observers.remove(observers.size() - 1));
                System.out.println("observers length: " + observers.size() + " stack length : " + undoStack.size());
            }
        }
        notifyObservers(gc);
    }

    public void redo(GraphicsContext gc){
        if(undoStack.size() > 0 && !updateUndo && !deleteUndo){
            System.out.println("UNDO FLAG SET, REDOING...");
            observers.add(undoStack.pop());
            notifyObservers(gc);
        }
    }

    public void setController(DrawController drawController) {
        this.drawController = drawController;
    }

    private void notifyObservers(GraphicsContext gc){
        for(DrawObserver observer : observers)
            observer.update(gc);
    }

    public Shape getShape(double x, double y){
        for(Shape observer : observers){
            if(observer.getX1() < x && observer.getX2() > x){
                if(observer.getY1() < y && observer.getY2() > y){
                    System.out.println("Found shape to get, the shape found has color: "+observer.getColor());
                    System.out.println("clicked on: " + observer.getClass().getName());
                    return observer;
                }//if
            }//f
        }//for
        return null;
    }//getShape

    public void updateShape(Shape shape, GraphicsContext gc, boolean isfilled){
        for(int i=0;i<observers.size();i++){
            if(observers.get(i).getX1() == shape.getX1() && observers.get(i).getY1() == shape.getY1() && observers.get(i).getY2() == shape.getY2() && observers.get(i).getX2() == shape.getX2()){
                System.out.println("Found shape to update, the shape found has color: "+observers.get(i).getColor());
                if(shape instanceof Circle){
                    Circle circle = (Circle) shape;
                    circle.setFilled(isfilled);
                }else if(shape instanceof Rectangle){
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setFilled(isfilled);
                }else if(shape instanceof Polygon){
                    Polygon polygon =(Polygon) shape;
                    polygon.setFilled(isfilled);
                }
                undoStack.push(observers.remove(i));
                updateUndo=true;
                observers.add(shape);
                System.out.println("ADDED SHAPE AND LINE WIDTH: "+shape.getLineWidth());
                break;
            }
        }
        notifyObservers(gc);
    }

    public void deleteShape(Shape shape, GraphicsContext gc){
        for(int i=0;i<observers.size();i++){
            if(observers.get(i).getX1() == shape.getX1() && observers.get(i).getY1() == shape.getY1() && observers.get(i).getY2() == shape.getY2() && observers.get(i).getX2() == shape.getX2()){
                System.out.println("Found shape to delete, the shape found has color: "+observers.get(i).getColor());
                undoStack.push(observers.remove(i));
                deleteUndo=true;
                break;
            }
        }
        notifyObservers(gc);
    }

    public void moveShape(double x1, double y1, double x2, double y2, GraphicsContext gc){
        Shape shape = getShape(x1, y1);
        System.out.println("Shape at: X1 = " + shape.getX1() + " Y1 = " + shape.getY1() + " X2 = " + shape.getX2() + " Y2 = " + shape.getY2());
        observers.remove(shape);
        double yMargin = shape.getY2() - shape.getY1();
        double xMargin = shape.getX2() - shape.getX1();
        shape.setX1(x2);
        shape.setY1(y2);
        shape.setX2(x2 + xMargin);
        shape.setX2(y2 + yMargin);
        updateShape(shape, gc, false);
        System.out.println("Shape is now at: X1 = " + shape.getX1() + " Y1 = " + shape.getY1() + " X2 = " + shape.getX2() + " Y2 = " + shape.getY2());
    }
}//class
