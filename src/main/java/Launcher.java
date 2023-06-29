import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("view/dashBorad.fxml" +
                "");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("WELCOME PIBT UNIVERSITY ");
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
           launch(args);
    }
}
