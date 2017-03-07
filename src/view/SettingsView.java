package view;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by dani on 2017-03-06.
 */
public class SettingsView extends Stage {

    public SettingsView(){
        init();
    }

    private void init(){
        BorderPane bp = new BorderPane();

        TextArea textArea = new TextArea("User Manual\n \n");
        textArea.appendText("Use the menu to edit shapes, choose shape to create with color, linewidth and other stuff.\n \n");
        textArea.appendText("Use the toolbar menu to customize the shape before creating it.\n \n");
        textArea.appendText("Click on edit shapes button and the choose shape to edit!");
        bp.setCenter(textArea);
        textArea.setEditable(false);
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(25);
        textArea.setWrapText(true);

        this.setScene(new Scene(bp));
        this.setTitle("Settings");
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
    }

}
