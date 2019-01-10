package eu.yeger.komi.controller;

import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScoreIndicatorController {

    private static final Color DEFAULT_COLOR = Color.web("#666666");

    private VBox vBox;

    private Player player;

    public void initialize(VBox vBox, Player player) {
        this.vBox = vBox;
        this.player = player;

        setColor(DEFAULT_COLOR, 0, vBox.getChildren().size());

        addListeners();
    }

    private void addListeners() {
        player.addPropertyChangeListener(Player.PROPERTY_score, evt -> {
            setColor(Model.getInstance().getGame().getPlayers().get(0).equals(player) ? Color.WHITE : Color.BLACK, Math.max(vBox.getChildren().size() - player.getScore(), 0), vBox.getChildren().size());
            setColor(DEFAULT_COLOR, 0, vBox.getChildren().size() - player.getScore());
        });
    }

    private void setColor(Color color, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            ((Circle) vBox.getChildren().get(i)).setFill(color);
        }
    }
}
