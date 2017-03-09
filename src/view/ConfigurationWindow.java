package view;

import controller.DrawControllerInterface;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

/**
 * Created by dani on 2017-03-06.
 */
public class ConfigurationWindow extends Stage{

    private Label color, lineWidth, fillUnfill;
    private RadioButton fillButton, unfillButton;
    private ColorPicker colorPicker;
    private SpinnerValueFactory<Double> valueFactory;
    private Button delete, ok;

    private DrawControllerInterface controller;
    private Canvas canvas;
    private Color colorValue;
    private double lineWidthVar;
    private boolean filled;
    private Shape shape;
    private GraphicsContext gc;

    public ConfigurationWindow(){

        GridPane gp = new GridPane();
        VBox vBox = new VBox();
        vBox.setPrefWidth(100);

        ToggleGroup group = new ToggleGroup();

        fillButton = new RadioButton("Fill Shape");
        fillButton.setToggleGroup(group);
        fillButton.setUserData("Fill Shape");

        unfillButton = new RadioButton("Unfill Shape");
        unfillButton.setToggleGroup(group);
        unfillButton.setUserData("Unfill Shape");

        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() == fillButton) {
                filled=true;
                System.out.println("FILL OPTION is : ");
            }else{
                filled=false;
                System.out.println("FIL OPTION is (false): ");
            }
        });

        colorPicker = new ColorPicker();
        colorPicker.setOnAction(event -> colorValue = colorPicker.getValue());

        Spinner<Double> spinner = new Spinner<>();
        valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 15, 1);
        spinner.setValueFactory(valueFactory);

        spinner.valueProperty().addListener((observable, oldValue, newValue) -> lineWidthVar = newValue );

        init();

        gp.add(color,0 ,0);
        gp.add(colorPicker, 1,0);
        gp.add(new Label(""), 0 , 1);
        gp.add(fillUnfill, 0 , 2);
        gp.add(fillButton,1,2);
        gp.add(unfillButton, 2, 2);
        gp.add(new Label(""), 0 , 3);
        gp.add(lineWidth, 0, 4);
        gp.add(spinner, 1, 4);
        gp.add(new Label(""),0, 5);
        gp.add(delete, 0, 6);
        gp.add(ok, 1, 6);
        this.setScene(new Scene(gp, 470,180));
        this.setTitle("Shape Configuration");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
    }

    private void init(){
        lineWidthVar = 1;
        colorValue = colorPicker.getValue();

        color = new Label("Pick color:");
        lineWidth = new Label("Choose line width:");
        fillUnfill = new Label("Choose to fill or unfill Shape:");

        delete = new Button("Delete Shape");
        delete.setOnAction(actionEvent -> {
            deleteShape();
            hide();
        });

        ok = new Button("save configuration");
        ok.setOnAction(actionEvent -> {
            saveConfig();
            hide();
        });

        this.setOnCloseRequest(we -> hide());
    }

    private void deleteShape(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        controller.deleteShape(shape, gc);
    }

    private void saveConfig(){
        shape.setColor(colorValue);
        shape.setLineWidth(lineWidthVar);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println("BEFORE UPDATE: "+" COLOR: "+colorValue + " width: "+lineWidthVar + " filld: "+filled);
        controller.updateShape(shape, gc, filled);
    }

    public void setController(DrawControllerInterface controller) { this.controller = controller; }

    public void setShape(Shape shape) { this.shape = shape; }

    public void setGc(GraphicsContext gc){ this.gc = gc; }

    public void setCanvas(Canvas canvas) { this.canvas = canvas; }

    public void operate(){
        colorPicker.setValue(shape.getColor());
        colorValue=shape.getColor();
        valueFactory.setValue(shape.getLineWidth());

        shape = controller.checkAndCreateShape(shape);
        if(shape instanceof Line){
            System.out.println("Config Line");
            unfillButton.setDisable(true);
            fillButton.setDisable(true);
            filled = false;
        }else{
            if(shape instanceof Circle){
                System.out.println("Config Circle");
                Circle circle = (Circle) shape;
                filled=circle.isFilled();
            }else if(shape instanceof Rectangle){
                System.out.println("Config Rectangle");
                Rectangle rectangle = (Rectangle) shape;
                filled = rectangle.isFilled();
            }else if(shape instanceof Polygon){
                System.out.println("Config Polygon");
                Polygon polygon = (Polygon) shape;
                filled=polygon.isFilled();
            }
            updateRadioButtons();
        }
    }

    private void updateRadioButtons(){
        if(fillButton.isDisable())
            fillButton.setDisable(false);
        if(unfillButton.isDisable())
            unfillButton.setDisable(false);

        if(filled) {
            fillButton.fire();
        }
        else {
            unfillButton.fire();
        }
    }
}
