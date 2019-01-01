package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

class GameLogicController {

    void turn(final Slot slot) {
        Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
        Pawn newPawn = new Pawn();
        newPawn.setPlayer(currentPlayer);
        slot.setPawn(newPawn);
        //TODO Implement grouping
        swapCurrentPlayer();
    }

    private void swapCurrentPlayer() {
        Game game = Model.getInstance().getGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player blackPlayer = game.getPlayers().get(0);
        Player whitePlayer = game.getPlayers().get(1);
        if (currentPlayer.equals(blackPlayer)) {
            game.setCurrentPlayer(whitePlayer);
        } else {
            game.setCurrentPlayer(blackPlayer);
        }
    }
}
