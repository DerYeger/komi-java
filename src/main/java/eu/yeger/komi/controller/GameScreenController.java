package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameScreenController {

    @FXML
    VBox gameScreenVBox;

    @FXML
    Label blackRoundsWonLabel;

    @FXML
    Label whiteRoundsWonLabel;

    private Player blackPlayer;

    private Player whitePlayer;

    public void initialize() {
        Game game = Model.getInstance().getGame();
        blackPlayer = game.getPlayers().get(0);
        whitePlayer = game.getPlayers().get(1);

        addListeners();

        updateBlackScoreLabel();
        updateWhiteScoreLabel();
    }

    private void addListeners() {
        blackPlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateBlackScoreLabel());
        whitePlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateWhiteScoreLabel());
    }

    private void updateBlackScoreLabel() {
        blackRoundsWonLabel.setText(Integer.toString(blackPlayer.getRoundsWon()));
    }

    private void updateWhiteScoreLabel() {
        whiteRoundsWonLabel.setText(Integer.toString(whitePlayer.getRoundsWon()));
    }
}
