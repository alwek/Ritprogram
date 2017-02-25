package view;

import controller.DrawController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;
import model.Rectangle;
import model.Shape;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by dani on 2017-02-25.
 */
public class View extends BorderPane{
    private FileClass fileClass;
    private GraphicsContext gc;
    private Canvas canvas;
    private int counter = 0;
    private double x1,x2,y1,y2;
    private DrawController controller;

    public View(FileClass fileClass){
        this.fileClass = fileClass;
        init();
        createMenu();
    }//View

    private void init(){
        Pane wrapperPane = new Pane();
        this.setCenter(wrapperPane);

        // Put canvas in the center of the window
        canvas = new Canvas();
        wrapperPane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        // Bind the width/height property to the wrapper Pane
        canvas.widthProperty().bind(wrapperPane.widthProperty());
        canvas.heightProperty().bind(wrapperPane.heightProperty());

        // redraw when resized
        canvas.widthProperty().addListener(event -> draw(canvas));
        canvas.heightProperty().addListener(event -> draw(canvas));
        draw(canvas);

        //Scene scene = new Scene(this, 300, 275);
        this.setOnMouseClicked(mouseHandler);
        this.setOnMouseDragged(mouseHandler);
        this.setOnMouseEntered(mouseHandler);
        this.setOnMouseExited(mouseHandler);
        this.setOnMouseMoved(mouseHandler);
        this.setOnMousePressed(mouseHandler);
        this.setOnMouseReleased(mouseHandler);
    }//init

    private MenuBar createMenu(){
        VBox vBox = new VBox();
        vBox.setPrefWidth(100);

        MenuBar menuBar = new MenuBar();
        vBox.getChildren().add(menuBar);
        this.setTop(vBox);

        // File menu - new, save, exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open file");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");

        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        saveMenuItem.setOnAction(arg0 -> {
            try {
                fileClass.saveFile();
                //Platform.exit();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        openFile.setOnAction(arg0 ->{ fileClass.openFile(); });
        fileMenu.getItems().addAll(newMenuItem, openFile ,saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // edit menu - Contains drawable shapes
        Menu editMenu = new Menu("Edit");

        CheckMenuItem lineMenuItem = new CheckMenuItem("Line");
        lineMenuItem.setSelected(true);
        editMenu.getItems().add(lineMenuItem);

        CheckMenuItem circleMenuItem = new CheckMenuItem("Circle");
        circleMenuItem.setSelected(true);
        editMenu.getItems().add(circleMenuItem);

        CheckMenuItem rectangleMenuItem = new CheckMenuItem("Rectangle");
        rectangleMenuItem.setSelected(true);
        editMenu.getItems().add(rectangleMenuItem);

        MenuItem clearMenuItem = new MenuItem("Clear");
        clearMenuItem.setOnAction(actionEvent -> clear());
        editMenu.getItems().add(clearMenuItem);

        // About menu - information about the program
        Menu aboutMenu = new Menu("About");
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
        mysqlItem.setToggleGroup(tGroup);

        RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
        oracleItem.setToggleGroup(tGroup);
        oracleItem.setSelected(true);
        aboutMenu.getItems().addAll(mysqlItem, oracleItem, new SeparatorMenuItem());

        Menu tutorialMenu = new Menu("Tutorial");
        tutorialMenu.getItems().addAll(
                new CheckMenuItem("Java"),
                new CheckMenuItem("JavaFX"),
                new CheckMenuItem("Swing"));

        aboutMenu.getItems().add(tutorialMenu);
        menuBar.getMenus().addAll(fileMenu, editMenu, aboutMenu);

        return menuBar;
    }//createMenuBar

    public void setController(DrawController controller){
        this.controller = controller;
    }

    private void exportImage(String imageName) {
        BufferedImage image = new BufferedImage((int) canvas.getWidth(), (int) canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        draw(canvas);
        try{
            System.out.println("Exporting image: " + imageName);
            FileOutputStream out = new FileOutputStream(imageName);
            ImageIO.write(image, "png", out);
            out.close();
        }//try
        catch (IOException e){
            e.printStackTrace();
        }//catch
    }//exportImage

    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                System.out.println("Mouse pressed");
                System.out.println("Counter: " + counter);
                if(counter == 1){
                    counter = 0;
                    x2 = mouseEvent.getX();
                    y2 = mouseEvent.getY();
                    drawRectangle(x1,y1,x2,y2);
                    drawLine(x1,y1,x2,y2);
                }//if
                else{
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    counter++;
                }//else
            }//if
        }//handle
    };

    /**
     * Draw crossed red lines which each each end is at the corner of window,
     * and 4 blue circles whose each center is at the corner of the window,
     * so that make it possible to know where is the extent the Canvas draws
     */
    private void draw(Canvas canvas) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(javafx.scene.paint.Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
        gc.setFill(javafx.scene.paint.Color.BLUE);
        gc.fillOval(-30, -30, 60, 60);
        gc.fillOval(-30 + width, -30, 60, 60);
        gc.fillOval(-30, -30 + height, 60, 60);
        gc.fillOval(-30 + width, -30 + height, 60, 60);
        //drawFromReload();
    }//draw on resize

    private void drawLine(double x1, double y1, double x2, double y2){
        Line line = new Line(x1, x2, y1, y2);
        controller.addShape(line);
        controller.drawShape(line, gc);
    }//draw on mouse event

    private void drawRectangle(double x1, double y1, double x2, double y2){
        Rectangle rectangle = new Rectangle(x1,x2,y1,y2);
        controller.addShape(rectangle);
        controller.drawShape(rectangle, gc);
    }//draw rectangle

    private void drawCircle(){

    }//draw circle

    public void drawFromReload(){
        try{
            ArrayList<Shape> list = (ArrayList<Shape>) controller.getShapeList();
            for(Shape aList : list) {
                aList.draw(gc);
            }//for
        }//try
        catch (NullPointerException ex){
            System.out.println("List is null");
        }//catch
    }//draw from a saved file

    private void clear(){
        init();
    }//clear
}//class