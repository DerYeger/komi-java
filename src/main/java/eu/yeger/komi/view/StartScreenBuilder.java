package eu.yeger.komi.view;

import eu.yeger.komi.controller.StartScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenBuilder {

    private static Parent startScreen = null;

    public static Parent getStartScreen(final Stage stage) throws IOException {
        if (startScreen == null) startScreen = buildStartScreenVBox(stage);
        return startScreen;
    }

    public static void clear() {
        startScreen = null;
    }

    private static VBox buildStartScreenVBox(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartScreenBuilder.class.getResource("startScreen.fxml"));
        VBox startScreenVBox = fxmlLoader.load();
        StartScreenController startScreenController = fxmlLoader.getController();
        startScreenController.initialize(stage);
        return startScreenVBox;
    }
}
