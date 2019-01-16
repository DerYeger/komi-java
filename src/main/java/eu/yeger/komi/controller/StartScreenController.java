package eu.yeger.komi.controller;

import eu.yeger.komi.view.GameScreenBuilder;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

import static eu.yeger.komi.util.ThrowingEventHandlerWrapper.throwingEventHandlerWrapper;

public class StartScreenController {

    private static final Integer[] BOARD_SIZES = {5, 7, 9};

    private static final Integer[] REQUIRED_SCORES = {3, 5, 7};

    private static final Integer[] REQUIRED_ROUNDS = {1, 2, 3, 4, 5};

    @FXML
    private ChoiceBox<Integer> boardSizeChoiceBox;

    @FXML
    private ChoiceBox<Integer> requiredScoreChoiceBox;

    @FXML
    private ChoiceBox<Integer> requiredRoundsChoiceBox;

    @FXML
    private Button startButton;

    private Stage stage;

    public void initialize(final Stage stage) {
        this.stage = stage;

        addChoiceBoxValues();

        addHandlers();
    }

    private void addChoiceBoxValues() {
        boardSizeChoiceBox.itemsProperty().setValue(FXCollections.observableArrayList(Arrays.asList(BOARD_SIZES)));
        boardSizeChoiceBox.getSelectionModel().selectFirst();

        requiredScoreChoiceBox.itemsProperty().setValue(FXCollections.observableArrayList(Arrays.asList(REQUIRED_SCORES)));
        requiredScoreChoiceBox.getSelectionModel().selectFirst();

        requiredRoundsChoiceBox.itemsProperty().setValue(FXCollections.observableArrayList(Arrays.asList(REQUIRED_ROUNDS)));
        requiredRoundsChoiceBox.getSelectionModel().select(2);
    }

    private void addHandlers() {
        startButton.setOnAction(throwingEventHandlerWrapper(event -> startGame()));
    }

    private void startGame() throws IOException {
        new GameController()
                .initGame(boardSizeChoiceBox.getValue(), requiredScoreChoiceBox.getValue(), requiredRoundsChoiceBox.getValue());
        stage.getScene().setRoot(GameScreenBuilder.getGameScreen(stage));
        stage.sizeToScene();
    }
}
