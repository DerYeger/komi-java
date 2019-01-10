package eu.yeger.komi.controller;

import eu.yeger.komi.model.Board;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Slot;

import static eu.yeger.komi.controller.ControllerUtilities.BOARD_SIZE;

class BoardController {

    void initBoard() {
        Board board = new Board();
        board.setGame(Model.getInstance().getGame()).setSize(BOARD_SIZE);

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                Slot slot = new Slot();
                slot.setXPos(x).setYPos(y).withNeighbors(getSlot(x, y - 1), getSlot(x - 1, y));

                board.withSlots(slot);
            }
        }
    }

    Slot getSlot(final int xPos, final int yPos) {
        int boardSize = Model.getInstance().getGame().getBoard().getSize();
        if (xPos >= boardSize || yPos >= boardSize || xPos < 0 || yPos < 0) return null;
        int index  = xPos + yPos * boardSize;
        if (index >= Model.getInstance().getGame().getBoard().getSlots().size()) return null;
        return Model.getInstance().getGame().getBoard().getSlots().get(index);
    }
}
