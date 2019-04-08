package visual.controllers;

import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.Cube;
import main.controllers.AlgorithmController;
import main.controllers.CubeController;
import visual.nodes.UnfoldedCubeCanvas;

public class MainController {
    private Cube cube;
    private CubeController cubeController;
    private AlgorithmController algorithmController2x2;
    @FXML
    private UnfoldedCubeCanvas cubeCanvas;
    @FXML
    private GridPane buttonPane;
    @FXML
    private Label label;
    @FXML
    private Button resetButton;
    @FXML
    public MenuItem simplifyLabel;

    @FXML
    public void initialize() {
        cube = new Cube();
        cubeController = new CubeController();
        algorithmController2x2 = new AlgorithmController(AlgorithmController.cube2x2x2);
        cubeCanvas.getGraphicsContext2D().setFill(Color.DARKGRAY);
        cubeCanvas.getGraphicsContext2D().fillRect(0, 0, cubeCanvas.getWidth(), cubeCanvas.getHeight());
        cubeCanvas.drawCube(cube);
        for (Node button : buttonPane.getChildren()) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                cubeController.rotate(cube, ((Button) button).getText());
                cubeCanvas.drawCube(cube);
                if (label.getText().equals("")) {
                    label.setText(((Button) button).getText());
                } else {
                    label.setText(label.getText() + " " + ((Button) button).getText());
                }
            });
        }
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            label.setText("");
            cube = new Cube();
            cubeCanvas.drawCube(cube);
        });
        simplifyLabel.setOnAction(event -> {
            String oldAlg = label.getText();
            System.out.println("alg: (" + oldAlg + ")");
            String newAlg = algorithmController2x2.simplify(oldAlg);
            label.setText(newAlg);
        });

    }
}
