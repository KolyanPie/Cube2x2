package visual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFx extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent panel = FXMLLoader.load(getClass().getResource("/visual/Main.fxml"));
        Scene scene =  new Scene(panel);
        primaryStage.setTitle("Cube 2x2 app");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(AppJavaFx.class, args);
    }
}
