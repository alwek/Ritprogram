package model;

import controller.DrawController;
import javafx.scene.canvas.GraphicsContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 2017-02-25.
 */
public class DrawModel {
    private List<DrawObserver> observers;
    private DrawController drawController;

    public DrawModel(){ observers = new ArrayList<>(); }//DrawModel

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
        ArrayList<DrawObserver> readObject = new ArrayList<>();
        observers.clear();
        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            readObject = (ArrayList<DrawObserver>) in.readObject();
            for(DrawObserver observer : readObject){
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


    public List<DrawObserver> getObservers(){
        return observers;
    }

    public void clearObservers(){ observers.clear(); }

    public void addShape(ShapeFactory shape, GraphicsContext gc, String selectedShape){
        if(selectedShape.equals("circle"))
            observers.add(shape.createCircle());
        else if(selectedShape.equals("line"))
            observers.add(shape.createLine());
        else if(selectedShape.equals("rectangle"))
            observers.add(shape.createRectangle());

        notifyObservers(gc);
    }//addShape

    public void removeShape(int index){

    }

    public void removeLatestShape(GraphicsContext gc){
        observers.remove(observers.size()-1);
        notifyObservers(gc);
    }

    public void setController(DrawController drawController) {
        this.drawController = drawController;
    }

    private void notifyObservers(GraphicsContext gc){
        for(DrawObserver observer : observers)
            observer.update(gc);
    }

}//class