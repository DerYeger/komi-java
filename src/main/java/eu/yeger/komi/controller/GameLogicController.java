package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static eu.yeger.komi.controller.ControllerUtilities.WINNING_SCORE;

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
        if (game.getCurrentPlayer().getScore() >= WINNING_SCORE) {
            new GameController().nextRound();
            return true;
        } else if (getWaitingPlayer().getScore() >= WINNING_SCORE) {
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
        setLiberties(player);

        //grant score gain
        int scoreGain = (int) player.getPawns().stream().filter(pawn -> !pawn.getHasLiberties()).count();
        Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
        currentPlayer.setScore(currentPlayer.getScore() + scoreGain);

        //remove pawns without liberties
        new ArrayList<>(player.getPawns()).stream().filter(pawn -> !pawn.getHasLiberties()).forEach(Pawn::removeYou);

        //remove liberties in preparation for next turn
        player.getPawns().forEach(pawn -> pawn.setHasLiberties(false));
    }

    private void setLiberties(final Player player) {
        Queue<Pawn> pawnQueue = new LinkedList<>();

        player.getPawns().stream().filter(pawn -> !pawn.getHasLiberties() && pawn.getSlot().getNeighbors().stream().anyMatch(otherSlot -> otherSlot.getPawn() == null)).forEach(pawn -> {
            pawn.setHasLiberties(true); //prevents pawns with liberties being added to the queue multiple times
            pawnQueue.add(pawn);
        });

        while (!pawnQueue.isEmpty()) {
            Pawn currentPawn = pawnQueue.poll();
            currentPawn.setHasLiberties(true);
            currentPawn.getSlot().getNeighbors().stream()
                    .filter(slot -> slot.getPawn() != null && slot.getPawn().getPlayer().equals(currentPawn.getPlayer()) && !slot.getPawn().getHasLiberties())
                    .forEach(slot -> pawnQueue.add(slot.getPawn()));
        }
    }
}
