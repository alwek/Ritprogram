package view;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Main extends Application{
    private GraphicsContext gc;
    private int counter = 0;
    private double x1,x2,y1,y2;

    public static void main(String[] args) {
        launch(args);
    }//main

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        // Put menu bar on the top of the window

        //MenuBar menuBar = new MenuBar(new Menu("File"), new Menu("Edit"), new Menu("Help"));
        MenuBar menuBar = initMenuBar(borderPane);
        borderPane.setTop(menuBar);

        // Create a wrapper Pane first
        Pane wrapperPane = new Pane();
        borderPane.setCenter(wrapperPane);

        // Put canvas in the center of the window
        Canvas canvas = new Canvas();
        wrapperPane.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        // Bind the width/height property to the wrapper Pane
        canvas.widthProperty().bind(wrapperPane.widthProperty());
        canvas.heightProperty().bind(wrapperPane.heightProperty());

        // redraw when resized
        canvas.widthProperty().addListener(event -> draw(canvas));
        canvas.heightProperty().addListener(event -> draw(canvas));
        draw(canvas);

        Scene scene = new Scene(borderPane, 300, 275);
        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);

        primaryStage.setTitle("Extreme Ultimate Drawing Elite Professional Tool Deluxe Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }//start

    private MenuBar initMenuBar(BorderPane root){
        MenuBar menuBar = new MenuBar();
        //menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // File menu - new, save, exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        Menu editMenu = new Menu("Edit");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
        htmlMenuItem.setSelected(true);
        editMenu.getItems().add(htmlMenuItem);

        CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
        cssMenuItem.setSelected(true);
        editMenu.getItems().add(cssMenuItem);

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
    }//initMenuBar

    /**
     * Draw crossed red lines which each each end is at the corner of window,
     * and 4 blue circles whose each center is at the corner of the window,
     * so that make it possible to know where is the extent the Canvas draws
     */
    private void draw(Canvas canvas) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
        gc.setFill(Color.BLUE);
        gc.fillOval(-30, -30, 60, 60);
        gc.fillOval(-30 + width, -30, 60, 60);
        gc.fillOval(-30, -30 + height, 60, 60);
        gc.fillOval(-30 + width, -30 + height, 60, 60);
    }//draw on resize

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
                    draw(x1,y1,x2,y2);
                }//if
                else{
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    counter++;
                }//else
            }//if
        }//handle
    };

    private void draw(double x1, double y1, double x2, double y2){
        gc.beginPath();
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }//draw on mouse event
}//class
