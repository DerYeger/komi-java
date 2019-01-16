package eu.yeger.komi;

import eu.yeger.komi.view.StartScreenBuilder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(StartScreenBuilder.getStartScreen(primaryStage)));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
