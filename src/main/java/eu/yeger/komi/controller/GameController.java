package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;

import java.util.ArrayList;

public class GameController {


    public void initGame(final int boardSize, final int requiredScore, final int requiredRounds ) {
        Model.resetModel();
        Game game = Model.getInstance().getGame();

        game.setScoreToWin(requiredScore).setRoundsToWin(requiredRounds)
                .withPlayers(new Player(), new Player())
                .setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));

        new BoardController().initBoard(game, boardSize);
    }

    void nextRoundWithWinner(final Player winner) {
        AudioController.playRoundOverAudioClip();

        Game game = Model.getInstance().getGame();
        game.setRound(game.getRound() + 1)
                .setCurrentPlayer(Model.getInstance().getGame().getPlayers().get(0));
        winner.setRoundsWon(winner.getRoundsWon() + 1);

        //removes leftover pawns
        game.getPlayers().forEach(player -> {
            player.setScore(0);
            new ArrayList<>(player.getPawns()).forEach(Pawn::removeYou);
        });

        if (playerHasReachedRoundLimit(winner)) game.setWinner(winner);
    }


    private boolean playerHasReachedRoundLimit(final Player player) {
        return player.getRoundsWon() == Model.getInstance().getGame().getRoundsToWin();
    }

    public void newGame() {
        Game game = Model.getInstance().getGame();

        game.setRound(0)
                .setWinner(null)
                .getPlayers().forEach(player ->
                    player.setRoundsWon(0)
                            .setScore(0));
    }
}
