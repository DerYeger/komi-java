package eu.yeger.komi.controller;

import eu.yeger.komi.model.Board;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Slot;

class BoardController {

    Board buildBoard() {
        Board board = new Board();
        for (int y = 0; y < ControllerUtilities.BOARD_SIZE; y++) {
            for (int x = 0; x < ControllerUtilities.BOARD_SIZE; x++) {
                Slot slot = new Slot();
                slot.setXPos(x).setYPos(y);
                board.withSlots(slot);
            }
        }
        return board;
    }

    Slot getSlot(final int xPos, final int yPos) {
        if (xPos >= ControllerUtilities.BOARD_SIZE || yPos >= ControllerUtilities.BOARD_SIZE || xPos < 0 || yPos < 0) return null;
        int index  = xPos + yPos * ControllerUtilities.BOARD_SIZE;
        return Model.getInstance().getGame().getBoard().getSlots().get(index);
    }
}
