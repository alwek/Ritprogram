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
    private List<Shape> shapeList;
    private DrawController drawController;

    public DrawModel(){
        shapeList = new ArrayList<>();
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
            if(out != null)
                out.writeObject(shapeList);
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
        shapeList = new ArrayList<>(); //tömmer listan för att hämta in en ny
        try {
            in = new ObjectInputStream(new FileInputStream(filename));

            // readObject returns a reference of type Object, hence the down-cast
            readObject = (ArrayList<Shape>) in.readObject();
            for(Shape b: readObject)
                shapeList.add(b);
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

    public List<Shape> getShapeList() {
        return shapeList;
    }//getShapeList

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }//setShapeList

    public void addShape(Shape shape){
        shapeList.add(shape);
    }//addShape

    public Shape getShape(int index){
        return shapeList.get(index);
    }//getShape

    public void drawShape(Shape shape, GraphicsContext gc) {
        shape.draw(gc);
    }//drawShape

    public void setController(DrawController drawController) {
        this.drawController = drawController;
    }
}//class