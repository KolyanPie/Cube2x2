package visual.nodes;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class RotateButton extends Button{
    public RotateButton() {
        super();
        setCursor(Cursor.OPEN_HAND);
        setPrefSize(64, 45);
        setFont(Font.font(20));
        setFocusTraversable(false);
    }
}
