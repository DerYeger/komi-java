package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameScreenController {

    @FXML
    Label blackScoreLabel;

    @FXML
    Label whiteScoreLabel;

    private Player blackPlayer;

    private Player whitePlayer;

    public void initialize() {
        Game game = Model.getInstance().getGame();
        blackPlayer = game.getPlayers().get(0);
        whitePlayer = game.getPlayers().get(1);

        addListeners();

        updateCurrentPlayerIndicator();
        updateBlackScoreLabel();
        updateWhiteScoreLabel();
    }

    private void addListeners() {
        Model.getInstance().getGame().addPropertyChangeListener(Game.PROPERTY_currentPlayer, evt -> updateCurrentPlayerIndicator());
        blackPlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateBlackScoreLabel());
        whitePlayer.addPropertyChangeListener(Player.PROPERTY_score, evt -> updateWhiteScoreLabel());
    }

    private void updateCurrentPlayerIndicator() {
        if (Model.getInstance().getGame().getCurrentPlayer().equals(blackPlayer)) {
            //show black
            blackScoreLabel.setStyle("-fx-font-size: 40");
            whiteScoreLabel.setStyle("-fx-font-size: 30");
        } else {
            //show white
            blackScoreLabel.setStyle("-fx-font-size: 30");
            whiteScoreLabel.setStyle("-fx-font-size: 40");
        }
    }

    private void updateBlackScoreLabel() {
        blackScoreLabel.setText(Integer.toString(blackPlayer.getScore()));
    }

    private void updateWhiteScoreLabel() {
        whiteScoreLabel.setText(Integer.toString(whitePlayer.getScore()));
    }
}
