package view;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DrawModel;

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
    private DrawModel drawModel;

    public FileClass(Stage stage, DrawModel drawModel){
        this.stage=stage;
        this.drawModel=drawModel;
    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Label fileInfoLabel = new Label("No file selected yet");
        fileInfoLabel.setPadding(new Insets(5, 5, 5, 5));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Files", "*.file"));

        file = fileChooser.showOpenDialog(stage);
        if (file != null){
            String path = file.getPath();
            fileInfoLabel.setText(path);
            try {
                drawModel.deSerializeFromFile(file.getName());
            } catch (IOException | ClassNotFoundException e) {
                showAlert("File chooser says " +e.getMessage());
                //System.out.println("File chooser says " +e.getMessage());
            }
        }else{
            System.out.println("Didnt choose file!");
        }
    }

    public void saveFile() throws IOException {
        drawModel.serializeToFile(getFileName());
    }

    public String getFileName(){
        if(file == null) {
           // return null; --> before
            System.out.println("Dialog box: "+fileInputBox());
        }
        return file.getName();
    }

    private String fileInputBox(){
       TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("File name input");
        dialog.setHeaderText("Enter the file name:");
        Optional<String> result = dialog.showAndWait();
        String fileName = "";

        if (result.isPresent() && result.get() != null) {
            fileName = result.get();
            file = new File(fileName+".txt");
        }
        return file.getName();
    }

    private void showAlert(String message) {
        alert.setHeaderText("Error!");
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.show();
    }
}
