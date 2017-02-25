package view;

import controller.DrawController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.DrawModel;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Main extends Application{
    private GraphicsContext gc;
    private Canvas canvas;
    private int counter = 0;
    private double x1,x2,y1,y2;

    public static void main(String[] args) {
        launch(args);
    }//main

    @Override
    public void start(Stage primaryStage) throws Exception {
        DrawModel model = new DrawModel();

        FileClass fileClass = new FileClass(primaryStage,model);
        View view = new View(fileClass);
        DrawController drawController = new DrawController(model, view);
        view.setController(drawController);
        model.setController(drawController);

        Scene scene = new Scene(view, 500, 375);
        primaryStage.setTitle("Extreme Ultimate Drawing Elite Professional Tool Deluxe Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }//start

}//class
