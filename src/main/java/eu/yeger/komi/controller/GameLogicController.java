package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class GameLogicController {

    void turn(final Slot slot) {
        Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
        Pawn newPawn = new Pawn();
        newPawn.setPlayer(currentPlayer);
        slot.setPawn(newPawn);

        checkAndRemovePawns(getWaitingPlayer());
        swapCurrentPlayer();
        checkAndRemovePawns(getWaitingPlayer());
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

    /*
    private void checkAndRemovePawns(final Player player) {
        player.getPawns().stream().forEach(pawn -> checkPawn(pawn));
        new ArrayList<>(player.getPawns()).stream().filter(pawn -> !pawn.getHasLiberties()).forEach(pawn -> pawn.removeYou());
        //TODO implement scoring
        player.getPawns().stream().forEach(pawn -> {
            pawn.setHasBeenChecked(false);
            pawn.setHasLiberties(false);
        });
    }
    */

    private void checkAndRemovePawns(final Player player) {
        for (Pawn pawn : player.getPawns()) {
            checkPawn(pawn);
        }
        new ArrayList<>(player.getPawns()).stream().filter(pawn -> !pawn.getHasLiberties()).forEach(Pawn::removeYou);
        player.getPawns().stream().forEach(pawn -> {
            pawn.setHasBeenChecked(false);
            pawn.setHasLiberties(false);
        });
    }

    private void checkPawn(final Pawn pawn) {

        if (pawn.getHasBeenChecked()) return;

        pawn.setHasBeenChecked(true);

        BoardController boardController = new BoardController();
        Slot slot = pawn.getSlot();
        int xPos = slot.getXPos();
        int yPos = slot.getYPos();

        Slot topSlot = boardController.getSlot(xPos, yPos - 1);
        Slot rightSlot = boardController.getSlot(xPos + 1, yPos);
        Slot bottomSlot = boardController.getSlot(xPos, yPos + 1);
        Slot leftSlot = boardController.getSlot(xPos - 1, yPos);


        //checks if pawn has empty neighboring slot (liberty)
        if (topSlot != null && topSlot.getPawn() == null ||
                rightSlot != null && rightSlot.getPawn() == null ||
                bottomSlot != null && bottomSlot.getPawn() == null ||
                leftSlot != null && leftSlot.getPawn() == null) {
            pawn.setHasBeenChecked(true);
            pawn.setHasLiberties(true);
        }

        Queue<Slot> slotQueue = new LinkedList<>();
        slotQueue.add(topSlot);
        slotQueue.add(rightSlot);
        slotQueue.add(bottomSlot);
        slotQueue.add(leftSlot);

        //checks if any neighboring slots have a pawn owned by the same player and if they have liberties
        while (!pawn.getHasLiberties() && !slotQueue.isEmpty()) {
            Slot otherSlot = slotQueue.poll();
            if (otherSlot != null && otherSlot.getPawn() != null && otherSlot.getPawn().getPlayer().equals(pawn.getPlayer())) {
                if (!otherSlot.getPawn().getHasBeenChecked()) checkPawn(otherSlot.getPawn());
                pawn.setHasLiberties(otherSlot.getPawn().getHasLiberties());
            }
        }
    }
}
