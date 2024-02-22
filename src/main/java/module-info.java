module ru.kolyanpie.cube {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.kolyanpie.cube to javafx.fxml;
    opens ru.kolyanpie.cube.visual.controllers to javafx.fxml;
    opens ru.kolyanpie.cube.visual.nodes to javafx.fxml;
    exports ru.kolyanpie.cube;
}
