package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 2017-02-25.
 */
public class DrawModel {

    private List<Shape> shapeList;

    public DrawModel(){ shapeList = new ArrayList<>(); }

    /**
     * saves all the information to the file path
     * @param filename
     * @throws IOException
     */
    public void serializeToFile(String filename) throws IOException {
        ObjectOutputStream out = null;
        //	ArrayList<Book> writeObject = new ArrayList<Book>();
        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            if(out !=null)
                out.writeObject(shapeList);
        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {}
        }
    }

    /**
     * load all the information from the file path
     * @param filename
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deSerializeFromFile(String filename) throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        ArrayList<Shape> readObject = new ArrayList<>();
        try {
            in = new ObjectInputStream(
                    new FileInputStream(filename));
            // readObject returns a reference of type Object, hence the down-cast
            readObject = (ArrayList<Shape>) in.readObject();
            for(Shape b: readObject)
                shapeList.add(b);
        }
        finally {
            try {
                if(in != null)	in.close();
            }catch(Exception e) {
                System.out.println("serialze says: " +e.getMessage());
            }
        }
    }
}