package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class GameLogicController {

    void turn(final Slot slot) {
        placePawn(getCurrentPlayer(), slot);

        AudioController.playPawnPlacedAudioClip();

        Player currentPlayer = getCurrentPlayer();
        Player waitingPlayer = getWaitingPlayer();

        completeTurnForPlayer(currentPlayer, waitingPlayer);
        if(playerHasReachedScoreLimit(currentPlayer)) {
            new GameController().nextRoundWithWinner(currentPlayer);
            return;
        }

        //checks for suicide moves
        completeTurnForPlayer(waitingPlayer, currentPlayer);
        if (playerHasReachedScoreLimit(waitingPlayer)) {
            new GameController().nextRoundWithWinner(waitingPlayer);
        } else {
            swapCurrentPlayer();
        }
    }

    private void placePawn(final Player player, final Slot slot) {
        new Pawn().setPlayer(player)
                .setSlot(slot);
    }

    private void completeTurnForPlayer(final Player player, final Player opponent) {
        grantLibertiesToPawns(opponent);
        grantScore(player, opponent);
        removePawnsWithoutLiberties(opponent);
        removeLibertiesFromPawns(opponent);
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

    private boolean playerHasReachedScoreLimit(final Player player) {
        return player.getScore() >= Model.getInstance().getGame().getScoreToWin();
    }
}
