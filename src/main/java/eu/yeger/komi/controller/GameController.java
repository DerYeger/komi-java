package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;

import java.util.ArrayList;

public class GameController {

    public void initGame() {
        Game game = Model.getInstance().getGame();

        Player blackPlayer = new Player(); //index 0
        Player whitePlayer = new Player(); //index 1
        game.withPlayers(blackPlayer, whitePlayer);

        startRound();
    }

    void nextRound() {
        Game game = Model.getInstance().getGame();
        game.getCurrentPlayer().setRoundsWon(game.getCurrentPlayer().getRoundsWon() + 1);
        //removes leftover pawns
        game.getPlayers().stream().forEach(player -> {
            player.setScore(0);
            new ArrayList<>(player.getPawns()).stream().forEach(Pawn::removeYou);
        });
        game.setRound(game.getRound() + 1);
        startRound();
    }

    void startRound() {
        Model.getInstance().getGame().setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));
        new BoardController().initBoard();
    }
}
