package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SlotController {

    @FXML
    Button slotButton;

    @FXML
    Line topLine;

    @FXML
    Line rightLine;

    @FXML
    Line bottomLine;

    @FXML
    Line leftLine;

    @FXML
    Circle pawnCircle;

    @FXML
    Circle selectionCircle;

    private Slot slot;

    public void initialize(final Slot slot) {
        this.slot = slot;
        int xPos = slot.getXPos();
        int yPos = slot.getYPos();

        topLine.setVisible(yPos > 0);
        rightLine.setVisible(xPos < ControllerUtilities.BOARD_SIZE - 1);
        bottomLine.setVisible(yPos < ControllerUtilities.BOARD_SIZE - 1);
        leftLine.setVisible(xPos > 0);

        addHandlers();
        addListeners();
    }

    private void addHandlers() {
        slotButton.setOnAction(event -> {
            if (slot.getPawn() == null) {
                new GameLogicController().turn(slot);
            }
        });
        slotButton.setOnMouseEntered(event -> updateSelectionCircle(true));
        slotButton.setOnMouseExited(event -> updateSelectionCircle(false));
    }

    private void addListeners() {
        slot.addPropertyChangeListener(Slot.PROPERTY_pawn, evt -> updatePawnCircle());
    }

    private void updatePawnCircle() {
        Pawn pawn = slot.getPawn();

        if (pawn != null) {
            pawnCircle.setVisible(true);
            Player possessingPlayer = pawn.getPlayer();
            if (Model.getInstance().getGame().getPlayers().get(0).equals(possessingPlayer)) {
                pawnCircle.setFill(Color.BLACK);
            } else {
                pawnCircle.setFill(Color.WHITE);
            }
        } else {
            pawnCircle.setVisible(false);
        }
    }

    private void updateSelectionCircle(final boolean visible) {
        selectionCircle.setVisible(visible && slot.getPawn() == null);

        if (visible && slot.getPawn() == null) {
            selectionCircle.setVisible(true);
            Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
            if (Model.getInstance().getGame().getPlayers().get(0).equals(currentPlayer)) {
                selectionCircle.setFill(Color.BLACK);
            } else {
                selectionCircle.setFill(Color.WHITE);
            }
        }
    }
}
