package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameLogicControllerTests {

    @Test
    public void removalTest() {
        GameController gc = new GameController();
        GameLogicController glc = new GameLogicController();
        BoardController bc =  new BoardController();

        gc.initGame();

        Game game = Model.getInstance().getGame();

        Player blackPlayer = game.getPlayers().get(0);
        Player whitePlayer = game.getPlayers().get(1);

        Pawn pB1 = new Pawn();
        Pawn pB2 = new Pawn();
        Pawn pB3 = new Pawn();
        blackPlayer.withPawns(pB1, pB2, pB3);
        Assert.assertEquals(3, blackPlayer.getPawns().size());

        Pawn pW1 = new Pawn();
        whitePlayer.withPawns(pW1);
        Assert.assertEquals(1, whitePlayer.getPawns().size());

        bc.getSlot(1, 0).setPawn(pB1); //above
        bc.getSlot(2, 1).setPawn(pB2); //right
        bc.getSlot(0, 1).setPawn(pB3); //left

        bc.getSlot(1, 1).setPawn(pW1); //middle

        Assert.assertEquals(blackPlayer, game.getCurrentPlayer());
        glc.turn(bc.getSlot(1, 2));

        Assert.assertEquals(4, blackPlayer.getPawns().size());
        Assert.assertTrue(whitePlayer.getPawns().isEmpty());
        Assert.assertNull( bc.getSlot(1, 1).getPawn());
    }
}
