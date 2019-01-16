package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import org.junit.Assert;
import org.junit.Test;

public class GameControllerTests {

    @Test
    public void initGameTest() {
        GameController gc = new GameController();

        gc.initGame(5, 3, 3);

        Game game = Model.getInstance().getGame();

        Assert.assertEquals(0, game.getRound());
        Assert.assertEquals(2, game.getPlayers().size());

        Player blackPlayer = game.getPlayers().get(0);
        Player whitePlayer = game.getPlayers().get(1);

        Assert.assertEquals(blackPlayer, game.getCurrentPlayer());

        Assert.assertEquals(0, blackPlayer.getScore());
        Assert.assertEquals(0, whitePlayer.getScore());

        Assert.assertEquals(0, blackPlayer.getRoundsWon());
        Assert.assertEquals(0, whitePlayer.getRoundsWon());

        Assert.assertTrue(blackPlayer.getPawns().isEmpty());
        Assert.assertTrue(blackPlayer.getPawns().isEmpty());

        Board board = game.getBoard();

        int boardSize = board.getSize();

        Assert.assertEquals(boardSize * boardSize, board.getSlots().size());
    }
}
