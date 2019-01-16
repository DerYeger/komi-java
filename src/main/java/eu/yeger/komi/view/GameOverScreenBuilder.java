package eu.yeger.komi.view;

import eu.yeger.komi.controller.GameOverScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverScreenBuilder {

    private static Parent gameOverScreen = null;

    public static Parent getGameOverScreen(final Stage stage) throws IOException {
        if (gameOverScreen == null) gameOverScreen = buildGameOverScreenVBox(stage);
        return gameOverScreen;
    }

    public static void clear() {
        gameOverScreen = null;
    }

    private static VBox buildGameOverScreenVBox(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameOverScreenBuilder.class.getResource("gameOverScreen.fxml"));
        VBox gameOverScreenVBox = fxmlLoader.load();
        GameOverScreenController gameOverScreenController = fxmlLoader.getController();
        gameOverScreenController.initialize(stage);
        return gameOverScreenVBox;
    }
}
