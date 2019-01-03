package eu.yeger.komi.view;

import eu.yeger.komi.controller.ControllerUtilities;
import eu.yeger.komi.controller.ScoreIndicatorController;
import eu.yeger.komi.controller.SlotController;
import eu.yeger.komi.model.*;

import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ViewBuilder {

    public static VBox buildGameScreenVBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("gameScreen.fxml"));
        VBox gameScreen = fxmlLoader.load();
        gameScreen.getChildren().add(buildGameScreenBoard());
        return gameScreen;
    }

    private static HBox buildGameScreenBoard() throws IOException {
        Game game = Model.getInstance().getGame();
        HBox hBox = new HBox(buildScoreIndicatorVBox(game.getPlayers().get(0)),
                buildBoardGridPane(game.getBoard()),
                buildScoreIndicatorVBox(game.getPlayers().get(1)));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        return hBox;
    }

    private static GridPane buildBoardGridPane(final Board board) throws IOException {
        GridPane gridPane = new GridPane();
        for (Slot slot : board.getSlots()) {
            AnchorPane slotAnchorPane = buildSlotAnchorPane(slot);
            gridPane.add(slotAnchorPane, slot.getXPos(), slot.getYPos());

        }
        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setBackground(new Background(new BackgroundImage(new Image(ViewBuilder.class.getResource("wood.bmp").toExternalForm(),
//                512, 512, true, true),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        return gridPane;
    }

    private static AnchorPane buildSlotAnchorPane(final Slot slot) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewBuilder.class.getResource("slotLarge.fxml"));
        AnchorPane slotAnchorPane = fxmlLoader.load();
        SlotController slotController = fxmlLoader.getController();
        slotController.initialize(slot);
        return slotAnchorPane;
    }

    private static VBox buildScoreIndicatorVBox(final Player player) {
        VBox vBox = new VBox(12);
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
        circle.setRadius(30);
        //circle.setFill(Model.getInstance().getGame().getPlayers().get(0).equals(player) ? Color.WHITE : Color.BLACK);
        //circle.setVisible(false);
        return circle;
    }
}
