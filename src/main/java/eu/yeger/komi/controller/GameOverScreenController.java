package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;
import eu.yeger.komi.view.GameOverScreenBuilder;
import eu.yeger.komi.view.GameScreenBuilder;
import eu.yeger.komi.view.StartScreenBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static eu.yeger.komi.util.ThrowingEventHandlerWrapper.throwingEventHandlerWrapper;

public class GameOverScreenController {

    @FXML
    private Label winnerLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button exitButton;

    private Stage stage;

    public void initialize(final Stage stage) {
        this.stage = stage;

        addListeners();
        addHandlers();

        updateLabelText();
    }

    private void addListeners() {
        Model.getInstance().getGame().addPropertyChangeListener(Game.PROPERTY_winner, evt -> updateLabelText());
    }

    private void addHandlers() {
        newGameButton.setOnAction(throwingEventHandlerWrapper(event -> newGame()));
        mainMenuButton.setOnAction(throwingEventHandlerWrapper(event -> displayMainMenu()));
        exitButton.setOnAction(event -> stage.close());
    }

    private void newGame() throws IOException {
        new GameController().newGame();
        stage.getScene().setRoot(GameScreenBuilder.getGameScreen(stage));
        stage.sizeToScene();
    }

    private void displayMainMenu() throws IOException {
        Model.getInstance().getGame().setWinner(null);
        GameScreenBuilder.clear();
        GameOverScreenBuilder.clear();
        stage.getScene().setRoot(StartScreenBuilder.getStartScreen(stage));
        stage.sizeToScene();
    }

    private void updateLabelText() {
        //TODO implement
        Game game = Model.getInstance().getGame();
        Player winner = game.getWinner();

        int playerNumber = game.getPlayers().get(0).equals(winner) ? 1 : 2;

        winnerLabel.setText("Player " + playerNumber + " has won!");
    }
}
