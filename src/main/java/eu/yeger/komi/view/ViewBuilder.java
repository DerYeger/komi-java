package eu.yeger.komi.view;

import eu.yeger.komi.controller.SlotController;
import eu.yeger.komi.model.Board;
import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Slot;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ViewBuilder {

    public static VBox buildGameScreenVBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("gameScreen.fxml"));
        VBox gameScreen = fxmlLoader.load();
        gameScreen.getChildren().add(buildBoardGridPane(Model.getInstance().getGame().getBoard()));
        return gameScreen;
    }

    public static GridPane buildBoardGridPane(Board board) throws IOException {
        GridPane gridPane = new GridPane();
        for (Slot slot : board.getSlots()) {
            AnchorPane slotAnchorPane = buildSlotAnchorPane(slot);
            gridPane.add(slotAnchorPane, slot.getXPos(), slot.getYPos());

        }
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    public static AnchorPane buildSlotAnchorPane(Slot slot) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("slot.fxml"));
        AnchorPane slotAnchorPane = fxmlLoader.load();
        SlotController slotController = fxmlLoader.getController();
        slotController.initialize(slot);
        return slotAnchorPane;
    }
}
