package view;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Main extends Application{
    private GraphicsContext gc;
    private int counter = 0;
    private double x1,x2,y1,y2;
    @FXML Canvas canvas;
    @FXML BorderPane borderPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        borderPane = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(borderPane, 300, 275);
        Node node = borderPane.lookup("#canvas");
        canvas = (Canvas) node;
        gc = canvas.getGraphicsContext2D();
        //borderPane.getChildren().add(canvas);

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

    public static void main(String[] args){
        //Launches the application
        launch(args);
    }//main

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
                }
                else{
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    counter++;
                }
            }//if
        }//handle
    };

    private void draw(double x1, double y1, double x2, double y2){
        gc.beginPath();
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }

}//class
