package eu.yeger.komi.controller;

import eu.yeger.komi.model.Board;
import eu.yeger.komi.model.Game;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Slot;

class BoardController {

    void initBoard(final Game game, final int size) {
        Board board = new Board();
        board.setGame(game)
                .setSize(size);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                new Slot().setBoard(board)
                        .setXPos(x)
                        .setYPos(y)
                        .withNeighbors(getSlot(x, y - 1), getSlot(x - 1, y));
            }
        }
    }

    Slot getSlot(final int xPos, final int yPos) {
        if (!slotExists(xPos, yPos)) return null;

        Board board = Model.getInstance().getGame().getBoard();
        int index = xPos + yPos * board.getSize();
        return board.getSlots().get(index);
    }

    private boolean slotExists(final int xPos, final int yPos) {
        int boardSize = Model.getInstance().getGame().getBoard().getSize();
        return xPos < boardSize && xPos >= 0
                && yPos < boardSize  && yPos >= 0;
    }
}
