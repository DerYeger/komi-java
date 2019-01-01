package eu.yeger.komi;

import eu.yeger.komi.controller.GameController;
import eu.yeger.komi.view.ViewBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameController gc = new GameController();
        gc.initGame();
        primaryStage.setScene(new Scene(ViewBuilder.buildGameScreenVBox()));
        primaryStage.show();
    }
}
