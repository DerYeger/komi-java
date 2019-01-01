package eu.yeger.komi.controller;

import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Pawn;
import eu.yeger.komi.model.Player;
import eu.yeger.komi.model.Slot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
            Player currentPlayer = Model.getInstance().getGame().getCurrentPlayer();
            Pawn newPawn = new Pawn();
            newPawn.setPlayer(currentPlayer);
            slot.setPawn(newPawn);
            //TODO Implement grouping
        });
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
}
