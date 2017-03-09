package view;

import controller.DrawController;
import controller.DrawControllerInterface;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by dani on 2017-02-25.
 */
public class FileClass{

    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private File file;
    private Stage stage;
    private DrawControllerInterface drawController;

    public FileClass(Stage stage){ this.stage=stage; }

    public void setDrawController(DrawControllerInterface drawController){ this.drawController = drawController; }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Label fileInfoLabel = new Label("No file selected yet");
        fileInfoLabel.setPadding(new Insets(5, 5, 5, 5));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));

        file = fileChooser.showOpenDialog(stage);
        if (file != null){
            String path = file.getPath();
            fileInfoLabel.setText(path);
            try {
                drawController.deSerializeFromFile(file.getName());
            } catch (IOException | ClassNotFoundException e) {
                showAlert("File chooser says " +e.getMessage());
                //System.out.println("File chooser says " +e.getMessage());
            }
        }else{
            System.out.println("Didnt choose file!");
        }
    }

    public void saveFile() throws IOException {
        drawController.serializeToFile(fileOption());
    }


    public String fileOption() throws IOException {
        if(file == null) {
            System.out.println("Dialog box: "+fileInputBox());
        }else if(file != null){
            System.out.println("New or old: "+newOrOld());
        }
        return getFileName();
    }

    private String newOrOld() throws IOException {
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);

        conf.setTitle("Confirmation file action");
        conf.setHeaderText("Save or create new file!");
        conf.setContentText("Do you want to save to the old file "+file.getName()+" or create a new file ?");
        ButtonType buttonTypeOne = new ButtonType("save");
        ButtonType buttonTypeTwo = new ButtonType("create");
        conf.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = conf.showAndWait();

        if(result.isPresent()) {
            if (result.get() == buttonTypeTwo) {
                System.out.println("SECOND");
                return fileInputBox();
            } else {
                System.out.println("NONE");
                return file.getName();
            }
        }
        return null;
    }

    private String fileInputBox(){
       TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("File name input");
        dialog.setHeaderText("Enter the file name:");

        Optional<String> result = dialog.showAndWait();

        String fileName = "";

        if (result.isPresent()) {
            fileName = result.get();
            file = new File(fileName+".txt");
            return file.getName();
        }
        return file.getName();
    }

    private void showAlert(String message) {
        alert.setHeaderText("Error!");
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.show();
    }

    public String getFileName(){ return file.getName(); }
}
