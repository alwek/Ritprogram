package view;

import controller.DrawController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
    private ConfigurationWindow configurationWindow;
    private GraphicsContext gc;
    private Canvas canvas;
    private int counter;
    private double x1,y1;
    private DrawController controller;

    private String selectedShape;
    private boolean fillOption;
    private Color colorOption;
    private double lineWidth;

    public View(FileClass fileClass, ConfigurationWindow configurationWindow){
        this.fileClass = fileClass;
        this.configurationWindow = configurationWindow;
        counter=0;
        VBox vBox = new VBox();
        this.setTop(vBox);
        vBox.setPrefWidth(100);
        init();
        createMenu(vBox);
    }//View

    public void setController(DrawController controller){ this.controller = controller; }

    private void init(){ createAndDrawCanvas(); }//init

     private void createAndDrawCanvas(){
         Pane wrapperPane = new Pane();
         this.setCenter(wrapperPane);
         canvas = new Canvas();

         canvas.setOnMouseClicked(mouseHandler);
         canvas.setOnMouseDragged(mouseHandler);
         canvas.setOnMouseEntered(mouseHandler);
         canvas.setOnMouseExited(mouseHandler);
         canvas.setOnMouseMoved(mouseHandler);
         canvas.setOnMousePressed(mouseHandler);
         canvas.setOnMouseReleased(mouseHandler);

         wrapperPane.getChildren().add(canvas);
         gc = canvas.getGraphicsContext2D();
         canvas.widthProperty().bind(wrapperPane.widthProperty());
         canvas.heightProperty().bind(wrapperPane.heightProperty());
         System.out.println("boolean resizible: "+canvas.isResizable());
         // redraw when resized
         canvas.widthProperty().addListener(event -> draw(canvas));
         canvas.heightProperty().addListener(event -> draw(canvas));
         draw(canvas);
     }

     private MenuBar createMenu(VBox vBox){
         ToggleGroup group = new ToggleGroup();

         RadioButton fillButton = new RadioButton("Fill Shape");
         fillButton.setToggleGroup(group);
         fillButton.setUserData("Fill Shape");
         fillButton.setDisable(true);
         RadioButton unfillButton = new RadioButton("Unfill Shape");
         unfillButton.setToggleGroup(group);
         unfillButton.setUserData("Unfill Shape");
         unfillButton.fire();
         unfillButton.setDisable(true);

         group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
             if (group.getSelectedToggle() == fillButton) {
                 fillOption = true;
                 System.out.println("FILL OPTION is : "+fillOption);
             }else{
                 fillOption = false;
                 System.out.println("FIL OPTION is (false): "+fillOption);
             }
         });

         Separator separator = new Separator();
         separator.setOrientation(Orientation.VERTICAL);

         Separator separator1 = new Separator();
         separator1.setOrientation(Orientation.VERTICAL);

         ColorPicker colorPicker = new ColorPicker();
         colorPicker.setValue(Color.BLACK);
         colorOption = Color.BLACK;
         colorPicker.setOnAction(event -> colorOption = colorPicker.getValue());

         Label label = new Label("LineWidth:");
         Spinner<Integer> spinner = new Spinner<>();
         SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);
         spinner.setValueFactory(valueFactory);
         spinner.valueProperty().addListener((observable, oldValue, newValue) -> lineWidth = newValue );

         FlowPane root = new FlowPane();
         root.setHgap(3);
         root.setVgap(3);
         root.setPadding(new Insets(3));
         root.getChildren().addAll(label, spinner);
         // toolbar ends..

         MenuBar menuBar = new MenuBar();
         vBox.getChildren().add(menuBar);

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
        CheckMenuItem mouseMenuItem = new CheckMenuItem("Mouse");
        editMenu.getItems().addAll(mouseMenuItem, new SeparatorMenuItem());

        CheckMenuItem lineMenuItem = new CheckMenuItem("Line");
        lineMenuItem.setSelected(true);
        editMenu.getItems().add(lineMenuItem);
        selectedShape = "line";

        CheckMenuItem circleMenuItem = new CheckMenuItem("Circle");
        editMenu.getItems().add(circleMenuItem);
        CheckMenuItem rectangleMenuItem = new CheckMenuItem("Rectangle");
        CheckMenuItem polygonMenuItem = new CheckMenuItem("Polygon");

        rectangleMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(true);
            selectedShape = "rectangle";
            fillButton.setDisable(false);
            unfillButton.setDisable(false);
            mouseMenuItem.setSelected(false);
        });
        lineMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(true);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(false);
            selectedShape = "line";
            fillButton.setDisable(true);
            unfillButton.setDisable(true);
            mouseMenuItem.setSelected(false);
        });
        circleMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(true);
            rectangleMenuItem.setSelected(false);
            selectedShape = "circle";
            fillButton.setDisable(false);
            unfillButton.setDisable(false);
            mouseMenuItem.setSelected(false);
        });

        polygonMenuItem.setOnAction(actionEvent ->{
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(false);
            polygonMenuItem.setSelected(true);
            selectedShape = "polygon";
            mouseMenuItem.setSelected(false);
        });

        mouseMenuItem.setOnAction(actionEvent -> {
            lineMenuItem.setSelected(false);
            circleMenuItem.setSelected(false);
            rectangleMenuItem.setSelected(false);
            polygonMenuItem.setSelected(false);
            mouseMenuItem.setSelected(true);
            selectedShape = "mouse";
            fillButton.setDisable(false);
            unfillButton.setDisable(false);
        });

        editMenu.getItems().add(rectangleMenuItem);
        editMenu.getItems().add(polygonMenuItem);
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

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(fillButton, unfillButton, separator, colorPicker,separator1,root);
        vBox.getChildren().add(toolBar);
        return menuBar;
     }//createMenuBar

    private EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                System.out.println("Mouse pressed");
                System.out.println("Counter: " + counter);

                if(selectedShape.equals("mouse")){
                    //get shapes in this location
                    System.out.println("MOUSE IN RIGHT AREA!");
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    Shape shape = controller.getShape(x1, y1);
                    if(shape != null) {
                        configurationWindow.show();
                        configurationWindow.setShape(shape);
                        configurationWindow.setGc(gc);
                        configurationWindow.operate();
                    }
                }
                else if(counter == 1){
                    counter = 0;
                    double x2 = mouseEvent.getX();
                    double y2 = mouseEvent.getY();
                    System.out.println("lineWidth : "+lineWidth);
                    switch (selectedShape) {
                        case "circle":
                            controller.addShape(new ShapeFactoryImpl(null, null, new Circle(x1, x2, y1, y2, 0, fillOption, colorOption, lineWidth), null).createCircle(), gc);
                            break;
                        case "line":
                            controller.addShape(new ShapeFactoryImpl(new Line(x1, x2, y1, y2, colorOption, lineWidth), null, null, null).createLine(), gc);
                            break;
                        case "rectangle":
                            controller.addShape(new ShapeFactoryImpl(null, new Rectangle(x1, x2, y1, y2, fillOption, colorOption, lineWidth), null, null).createRectangle(), gc);
                            break;
                        case "polygon":
                            System.out.println("POLYGON!");
                            controller.addShape(new ShapeFactoryImpl(null, null, null, new Polygon(x1, x2, y1, y2, fillOption, colorOption, lineWidth)).createPolygon(), gc);
                            break;
                    }
                }//if
                else{
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    counter++;
                }//else
            }//if
        }//handle
    };

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