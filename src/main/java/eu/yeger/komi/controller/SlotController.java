package eu.yeger.komi.controller;

import eu.yeger.komi.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SlotController {

    @FXML
    private Button slotButton;

    @FXML
    private Line topLine;

    @FXML
    private Line rightLine;

    @FXML
    private Line bottomLine;

    @FXML
    private Line leftLine;

    @FXML
    private Circle pawnCircle;

    @FXML
    private Circle selectionCircle;

    private Slot slot;

    public void initialize(final Slot slot) {
        this.slot = slot;
        int xPos = slot.getXPos();
        int yPos = slot.getYPos();

        int boardSize = Model.getInstance().getGame().getBoard().getSize();

        topLine.setVisible(yPos > 0);
        rightLine.setVisible(xPos < boardSize - 1);
        bottomLine.setVisible(yPos < boardSize - 1);
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
