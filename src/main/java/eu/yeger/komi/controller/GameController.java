package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public class GameController {

    public void initGame() {
        Game game = Model.getInstance().getGame();

        Player blackPlayer = new Player(); //index 0
        Player whitePlayer = new Player(); //index 1
        game.withPlayers(blackPlayer, whitePlayer);
        game.setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));

        new BoardController().initBoard();
    }

    void nextRound() {
        new AudioClip(getClass().getResource("/sounds/round_over.wav").toExternalForm()).play();

        Game game = Model.getInstance().getGame();
        game.setRound(game.getRound() + 1);
        game.getCurrentPlayer().setRoundsWon(game.getCurrentPlayer().getRoundsWon() + 1);
        game.setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));

        //removes leftover pawns
        game.getPlayers().stream().forEach(player -> {
            player.setScore(0);
            new ArrayList<>(player.getPawns()).stream().forEach(Pawn::removeYou);
        });
    }
}
