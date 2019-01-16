package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;

import eu.yeger.komi.view.GameOverScreenBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameScreenController {

    @FXML
    private Label blackRoundsWonLabel;

    @FXML
    private Label whiteRoundsWonLabel;

    private Stage stage;

    private Player blackPlayer;

    private Player whitePlayer;

    public void initialize(final Stage stage) {
        Objects.requireNonNull(stage);
        this.stage = stage;

        Game game = Model.getInstance().getGame();
        blackPlayer = game.getPlayers().get(0);
        whitePlayer = game.getPlayers().get(1);

        addListeners();

        updateBlackScoreLabel();
        updateWhiteScoreLabel();
    }

    private void addListeners() {
        Game game = Model.getInstance().getGame();
        game.addPropertyChangeListener(Game.PROPERTY_winner, evt -> {
            if (game.getWinner() != null) {
                try {
                    endGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        blackPlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateBlackScoreLabel());
        whitePlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateWhiteScoreLabel());
    }

    private void endGame() throws IOException {
        blackRoundsWonLabel.setText("0");
        whiteRoundsWonLabel.setText("0");
        stage.getScene().setRoot(GameOverScreenBuilder.getGameOverScreen(stage));
        stage.sizeToScene();
    }

    private void updateBlackScoreLabel() {
        blackRoundsWonLabel.setText(Integer.toString(blackPlayer.getRoundsWon()));
    }

    private void updateWhiteScoreLabel() {
        whiteRoundsWonLabel.setText(Integer.toString(whitePlayer.getRoundsWon()));
    }
}
