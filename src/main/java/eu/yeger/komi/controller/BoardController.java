package eu.yeger.komi.controller;

import eu.yeger.komi.model.Board;
import eu.yeger.komi.model.Slot;

public class BoardController {

    public Board buildBoard() {
        Board board = new Board();
        for (int x = 0; x < ControllerUtilities.BOARD_SIZE; x++) {
            for (int y = 0; y < ControllerUtilities.BOARD_SIZE; y++) {
                Slot slot = new Slot();
                slot.setXPos(x).setYPos(y);
                board.withSlots(slot);
            }
        }
        return board;
    }
}
