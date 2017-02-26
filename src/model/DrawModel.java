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
    private List<ShapeFactory> shapeList;
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
        ArrayList<ShapeFactory> readObject = new ArrayList<>();
        shapeList = new ArrayList<>(); //tömmer listan för att hämta in en ny
        try {
            in = new ObjectInputStream(new FileInputStream(filename));

            // readObject returns a reference of type Object, hence the down-cast
            readObject = (ArrayList<ShapeFactory>) in.readObject();
            for(ShapeFactory b: readObject)
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

    public List<ShapeFactory> getShapeList() {
        return shapeList;
    }//getShapeList

    public void clearShapeList(){ shapeList.clear(); }

    public void addShape(ShapeFactory shape){
        shapeList.add(shape);
    }//addShape

    public void drawShape(ShapeFactory shape, GraphicsContext gc) {
        shape.createLine().draw(gc);
        shape.createRectangle().draw(gc);
        shape.createCircle().draw(gc);
    }//drawShape

    public void setController(DrawController drawController) {
        this.drawController = drawController;
    }
}//class