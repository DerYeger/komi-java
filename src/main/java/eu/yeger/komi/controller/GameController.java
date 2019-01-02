package eu.yeger.komi.controller;

import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;

public class GameController {

    public void initGame() {
        Game game = Model.getInstance().getGame();

        new BoardController().initBoard();

        Player blackPlayer = new Player();
        Player whitePlayer = new Player();
        game.withPlayers(blackPlayer, whitePlayer);
        game.setCurrentPlayer(blackPlayer);
    }
}
