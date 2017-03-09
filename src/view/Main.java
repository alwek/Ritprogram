package view;

import controller.DrawController;
import controller.DrawControllerInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DrawModel;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }//main

    @Override
    public void start(Stage primaryStage) throws Exception {
        DrawModel model = new DrawModel();

        FileClass fileClass = new FileClass(primaryStage);
        ConfigurationWindow configurationWindow = new ConfigurationWindow();
        SettingsView settingsView = new SettingsView();
        View view = new View(fileClass, configurationWindow, settingsView);
        DrawControllerInterface drawController = new DrawController(model, view);
        view.setController(drawController);
        model.setController(drawController);
        fileClass.setDrawController(drawController);
        configurationWindow.setController(drawController);

        Scene scene = new Scene(view, 900, 675);
        primaryStage.setTitle("Extreme Ultimate Drawing Elite Professional Tool Deluxe Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }//start
}//class
