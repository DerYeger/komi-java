package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static eu.yeger.komi.controller.ControllerUtilities.WINNING_SCORE;

class GameLogicController {

    void turn(final Slot slot) {
        placePawn(getCurrentPlayer(), slot);

        AudioController.playPawnPlacedAudioClip();

        completeTurnForPlayer(getCurrentPlayer(), getWaitingPlayer());
        if(roundIsOver()) return;

        swapCurrentPlayer();

        //checks for suicide moves
        completeTurnForPlayer(getCurrentPlayer(), getWaitingPlayer());
        roundIsOver();
    }

    private void placePawn(final Player player, final Slot slot) {
        new Pawn().setPlayer(player)
                .setSlot(slot);
    }

    private void completeTurnForPlayer(final Player currentPlayer, final Player waitingPlayer) {
        grantLibertiesToPawns(waitingPlayer);
        grantScore(currentPlayer, waitingPlayer);
        removePawnsWithoutLiberties(waitingPlayer);
        removeLibertiesFromPawns(waitingPlayer);
    }

    private void grantLibertiesToPawns(final Player player) {
        Queue<Pawn> pawnQueue = new LinkedList<>();

        player.getPawns().stream()
                .filter(pawn -> !pawn.getHasLiberties() &&
                        pawn.getSlot().getNeighbors().stream()
                                .anyMatch(otherSlot -> otherSlot.getPawn() == null))
                .forEach(pawn -> {
                    pawn.setHasLiberties(true); //prevents pawns with liberties being added to the queue multiple times
                    pawnQueue.add(pawn);
                });

        while (!pawnQueue.isEmpty()) {
            Pawn currentPawn = pawnQueue.poll();
            currentPawn.setHasLiberties(true);
            currentPawn.getSlot().getNeighbors().stream()
                    .filter(slot -> slot.getPawn() != null &&
                            slot.getPawn().getPlayer().equals(currentPawn.getPlayer()) && !slot.getPawn().getHasLiberties())
                    .forEach(slot -> pawnQueue.add(slot.getPawn()));
        }
    }

    private void grantScore(final Player player, final Player opponent) {
        int scoreGain = (int) opponent.getPawns().stream()
                .filter(pawn -> !pawn.getHasLiberties()).count();
        player.setScore(player.getScore() + scoreGain);
    }

    private void removePawnsWithoutLiberties(final Player player) {
        new ArrayList<>(player.getPawns()).stream()
                .filter(pawn -> !pawn.getHasLiberties())
                .forEach(Pawn::removeYou);
    }

    private void removeLibertiesFromPawns(final Player player) {
        player.getPawns()
                .forEach(pawn -> pawn.setHasLiberties(false));
    }

    private Player getCurrentPlayer() {
        return Model.getInstance().getGame().getCurrentPlayer();
    }

    private Player getWaitingPlayer() {
        Game game = Model.getInstance().getGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player playerOne = game.getPlayers().get(0);
        Player playerTwo = game.getPlayers().get(1);
        if (currentPlayer.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    private void swapCurrentPlayer() {
        Model.getInstance().getGame().setCurrentPlayer(getWaitingPlayer());
    }

    private boolean roundIsOver() {
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
}
