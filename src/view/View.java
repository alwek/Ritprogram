package view;

import controller.DrawController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dani on 2017-02-25.
 */
public class View extends BorderPane{
    private FileClass fileClass;
    private GraphicsContext gc;
    private Canvas canvas;
    private int counter = 0;
    private double x1,y1;
    private DrawController controller;
    private String selectedShape;

    public View(FileClass fileClass){
        this.fileClass = fileClass;
        init();
        createMenu();
    }//View

    public void setController(DrawController controller){ this.controller = controller; }

    private void init(){
        createAndDrawCanvas();
        this.setOnMouseClicked(mouseHandler);
        this.setOnMouseDragged(mouseHandler);
        this.setOnMouseEntered(mouseHandler);
        this.setOnMouseExited(mouseHandler);
        this.setOnMouseMoved(mouseHandler);
        this.setOnMousePressed(mouseHandler);
        this.setOnMouseReleased(mouseHandler);
    }//init

     private void createAndDrawCanvas(){
         Pane wrapperPane = new Pane();

         this.setCenter(wrapperPane);

         // Put canvas in the center of the window
         canvas = new Canvas();

         wrapperPane.getChildren().add(canvas);

         gc = canvas.getGraphicsContext2D();

         // Bind the width/height property to the wrapper Pane
         canvas.widthProperty().bind(wrapperPane.widthProperty());
         canvas.heightProperty().bind(wrapperPane.heightProperty());
         System.out.println("boolean resizible: "+canvas.isResizable());
         // redraw when resized
         canvas.widthProperty().addListener(event -> draw(canvas));
         canvas.heightProperty().addListener(event -> draw(canvas));
         draw(canvas);
     }

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

        newMenuItem.setOnAction(actionEvent ->{
            clearCanvas();
            controller.clearObserversList();
        });
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
        selectedShape = "line";

        CheckMenuItem circleMenuItem = new CheckMenuItem("Circle");
        //circleMenuItem.setSelected(true);
        editMenu.getItems().add(circleMenuItem);

        CheckMenuItem rectangleMenuItem = new CheckMenuItem("Rectangle");
        rectangleMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(true);
            selectedShape = "rectangle";
        });
        lineMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(true);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(false);
            selectedShape = "line";
        });
        circleMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(true);
            rectangleMenuItem.setSelected(false);
            selectedShape = "circle";
        });
        //rectangleMenuItem.setSelected(true);
        editMenu.getItems().add(rectangleMenuItem);
        editMenu.getItems().add(new SeparatorMenuItem());

        MenuItem clearMenuItem = new MenuItem("Clear");
        clearMenuItem.setOnAction(actionEvent -> {
            clearCanvas();
            controller.clearObserversList();
        });
        editMenu.getItems().add(clearMenuItem);

        MenuItem undoMenuItem = new MenuItem("Undo");
        undoMenuItem.setOnAction(actionEvent -> {
            System.out.println("Undoing latest shape");
            clearCanvas();
            controller.removeLatestShape(gc);
        });

        MenuItem redoMenuItem = new MenuItem("Redo");
        redoMenuItem.setOnAction(actionEvent ->{
            System.out.println("Redoing latest shape");
            controller.redo(gc);
        });

        editMenu.getItems().add(undoMenuItem);
        editMenu.getItems().add(redoMenuItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);
        return menuBar;
    }//createMenuBar

    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                System.out.println("Mouse pressed");
                System.out.println("Counter: " + counter);
                if(counter == 1){
                    counter = 0;
                    double x2 = mouseEvent.getX();
                    double y2 = mouseEvent.getY();
                    ShapeFactory shapeFactory = new ShapeFactoryImpl(new Line(x1,x2,y1,y2, Color.BLACK), new Rectangle(x1,x2,y1,y2, true, Color.RED), new Circle(x1,x2,y1,y2,0, false,Color.BLACK));
                    controller.addShape(shapeFactory, gc, selectedShape);
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
        drawFromReload();
    }//draw on resize

    public void drawFromReload(){
        try{
            ArrayList<Shape> list = (ArrayList<Shape>) controller.getObservers();
            if(list.size() > 0)
                clearCanvas();
            for(DrawObserver aList : list) {
                aList.update(gc);
            }//for
        }//try
        catch (NullPointerException ex){
            System.out.println("List is null");
        }//catch
    }//draw from a saved file

    private void clearCanvas(){ gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); }//clear
}//class
