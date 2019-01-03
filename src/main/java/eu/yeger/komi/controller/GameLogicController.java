package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import javafx.scene.media.AudioClip;

import java.util.ArrayList;

class GameLogicController {

    void turn(final Slot slot) {
        Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
        Pawn newPawn = new Pawn();
        newPawn.setPlayer(currentPlayer);
        slot.setPawn(newPawn);

        new AudioClip(getClass().getResource("/sounds/pawn_placed.wav").toExternalForm()).play();

        checkAndRemovePawns(getWaitingPlayer());
        if(checkIfRoundIsOver()) return;

        swapCurrentPlayer();

        //checks for suicide moves
        checkAndRemovePawns(getWaitingPlayer());
        checkIfRoundIsOver();
    }

    private boolean checkIfRoundIsOver() {
        Game game = Model.getInstance().getGame();
        if (game.getCurrentPlayer().getScore() >= ControllerUtilities.WINNING_SCORE) {
            new GameController().nextRound();
            return true;
        } else if (getWaitingPlayer().getScore() >= ControllerUtilities.WINNING_SCORE) {
            swapCurrentPlayer();
            new GameController().nextRound();
            return true;
        }
        return false;
    }

    private void swapCurrentPlayer() {
        Model.getInstance().getGame().setCurrentPlayer(getWaitingPlayer());
    }

    private Player getWaitingPlayer() {
        Game game = Model.getInstance().getGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player blackPlayer = game.getPlayers().get(0);
        Player whitePlayer = game.getPlayers().get(1);
        if (currentPlayer.equals(blackPlayer)) {
            return whitePlayer;
        } else {
            return blackPlayer;
        }
    }

    private void checkAndRemovePawns(final Player player) {
        player.getPawns().stream().forEach(this::checkPawn);

        int scoreGain = (int) player.getPawns().stream().filter(pawn -> !pawn.getHasLiberties()).count();

        Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
        currentPlayer.setScore(currentPlayer.getScore() + scoreGain);

        new ArrayList<>(player.getPawns()).stream().filter(pawn -> !pawn.getHasLiberties()).forEach(Pawn::removeYou);

        player.getPawns().stream().forEach(pawn -> pawn.setHasLiberties(false));
    }

    //if any neighboring slot is empty (and thus a liberty)
    //notify all neighboring and friendly pawns that they possess that liberty as well
    private void checkPawn(final Pawn pawn) {
        if (!pawn.getHasLiberties() && pawn.getSlot().getNeighbors().stream().anyMatch(otherSlot -> otherSlot.getPawn() == null)) {
            grantAndPropagateLiberties(pawn);
        }
    }

    private void grantAndPropagateLiberties(final Pawn pawn) {
        pawn.setHasLiberties(true);

        //recursively mark all connected pawns belonging to the same player that are not marked already
        pawn.getSlot().getNeighbors().stream()
                .filter(slot -> slot.getPawn() != null && slot.getPawn().getPlayer().equals(pawn.getPlayer()) && !slot.getPawn().getHasLiberties())
                .forEach(slot -> grantAndPropagateLiberties(slot.getPawn()));
    }
}
