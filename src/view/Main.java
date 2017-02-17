package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by alica on 2017-02-17.
 * Good luck, Commander!
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Extreme Ultimate Drawing Elite Professional Tool Deluxe Edition");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        //opens the window
    }//start

    public static void main(String[] args){
        //Launches the application
        launch(args);
    }//main
}//class
