package eu.yeger.komi.view;

import eu.yeger.komi.controller.GameScreenController;
import eu.yeger.komi.controller.ScoreIndicatorController;
import eu.yeger.komi.controller.SlotController;
import eu.yeger.komi.model.*;

import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreenBuilder {

    private static Parent gameScreen = null;

    public static Parent getGameScreen(final Stage stage) throws IOException {
        if (gameScreen == null) gameScreen = buildGameScreenVBox(stage);
        return gameScreen;
    }

    public static void clear() {
        gameScreen = null;
    }

    private static VBox buildGameScreenVBox(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScreenBuilder.class.getResource("gameScreen.fxml"));
        VBox gameScreen = fxmlLoader.load();
        GameScreenController gameScreenController = fxmlLoader.getController();
        gameScreenController.initialize(stage);
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
        return gridPane;
    }

    private static AnchorPane buildSlotAnchorPane(final Slot slot) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScreenBuilder.class.getResource("slotLarge.fxml"));
        AnchorPane slotAnchorPane = fxmlLoader.load();
        SlotController slotController = fxmlLoader.getController();
        slotController.initialize(slot);
        return slotAnchorPane;
    }

    private static VBox buildScoreIndicatorVBox(final Player player) {
        VBox vBox = new VBox(12);
        for (int i = 0; i < Model.getInstance().getGame().getScoreToWin(); i++) {
            vBox.getChildren().add(new Circle(30));
        }
        new ScoreIndicatorController().initialize(vBox, player);
        vBox.setAlignment(Pos.CENTER);
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return vBox;
    }
}
