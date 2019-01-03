package eu.yeger.komi.view;

import eu.yeger.komi.controller.ControllerUtilities;
import eu.yeger.komi.controller.ScoreIndicatorController;
import eu.yeger.komi.controller.SlotController;
import eu.yeger.komi.model.*;

import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ViewBuilder {

    public static VBox buildGameScreenVBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("gameScreen.fxml"));
        VBox gameScreen = fxmlLoader.load();
        gameScreen.getChildren().add(buildGameScreenBoard());
        return gameScreen;
    }

    public static HBox buildGameScreenBoard() throws IOException {
        Game game = Model.getInstance().getGame();
        HBox hBox = new HBox(buildScoreIndicatorVBox(game.getPlayers().get(0)),
                buildBoardGridPane(game.getBoard()),
                buildScoreIndicatorVBox(game.getPlayers().get(1)));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        return hBox;
    }

    private static GridPane buildBoardGridPane(final Board board) throws IOException {
        GridPane gridPane = new GridPane();
        for (Slot slot : board.getSlots()) {
            AnchorPane slotAnchorPane = buildSlotAnchorPane(slot);
            gridPane.add(slotAnchorPane, slot.getXPos(), slot.getYPos());

        }
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private static AnchorPane buildSlotAnchorPane(final Slot slot) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("slot.fxml"));
        AnchorPane slotAnchorPane = fxmlLoader.load();
        SlotController slotController = fxmlLoader.getController();
        slotController.initialize(slot);
        return slotAnchorPane;
    }

    private static VBox buildScoreIndicatorVBox(final Player player) {
        VBox vBox = new VBox(4);
        for (int i = 0; i < ControllerUtilities.WINNING_SCORE; i++) {
            vBox.getChildren().add(buildScoreIndicatorCircle(player));
        }
        new ScoreIndicatorController().initialize(vBox, player);
        vBox.setAlignment(Pos.CENTER);
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return vBox;
    }

    private static Circle buildScoreIndicatorCircle(final Player player) {
        Circle circle =  new Circle();
        circle.setRadius(23);
        circle.setFill(Model.getInstance().getGame().getPlayers().get(0).equals(player) ? Color.WHITE : Color.BLACK);
        circle.setVisible(false);
        return circle;
    }
}
