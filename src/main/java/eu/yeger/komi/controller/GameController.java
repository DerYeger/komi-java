package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;

import java.util.ArrayList;

import static eu.yeger.komi.controller.ControllerUtilities.BOARD_SIZE;

public class GameController {

    public void initGame() {
        Model.resetModel();
        Game game = Model.getInstance().getGame();

        Player blackPlayer = new Player(); //index 0
        Player whitePlayer = new Player(); //index 1
        game.withPlayers(blackPlayer, whitePlayer);
        game.setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));

        new BoardController().initBoard(game, BOARD_SIZE);
    }

    void nextRoundWithWinner(final Player winner) {
        AudioController.playRoundOverAudioClip();

        Game game = Model.getInstance().getGame();
        game.setRound(game.getRound() + 1);
        winner.setRoundsWon(winner.getRoundsWon() + 1);
        game.setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));

        //removes leftover pawns
        game.getPlayers().forEach(player -> {
            player.setScore(0);
            new ArrayList<>(player.getPawns()).forEach(Pawn::removeYou);
        });
    }
}
