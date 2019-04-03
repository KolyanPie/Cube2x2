package visual.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.Cube;
import main.controllers.CubeController;
import visual.nodes.UnfoldedCubeCanvas;

public class MainController {
    private Cube cube;
    private CubeController cubeController;
    @FXML
    private UnfoldedCubeCanvas cubeCanvas;
    @FXML
    private GridPane buttonPane;
    @FXML
    private Label label;
    @FXML
    private Button resetButton;

    @FXML
    public void initialize() {
        cube = new Cube();
        cubeController = new CubeController();
        cubeCanvas.getGraphicsContext2D().setFill(Color.DARKGRAY);
        cubeCanvas.getGraphicsContext2D().fillRect(0, 0, cubeCanvas.getWidth(), cubeCanvas.getHeight());
        cubeCanvas.drawCube(cube);
        for (Node button : buttonPane.getChildren()) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                cubeController.rotate(cube, ((Button) button).getText());
                cubeCanvas.drawCube(cube);
                label.setText(label.getText() + " " + ((Button) button).getText());
            });
        }
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            label.setText("");
            cube = new Cube();
            cubeCanvas.drawCube(cube);
        });
    }
}
