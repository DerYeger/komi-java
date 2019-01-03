package eu.yeger.komi.controller;

import eu.yeger.komi.model.Player;
import javafx.scene.layout.VBox;

public class ScoreIndicatorController {

    public void initialize(VBox vBox, Player player) {
        player.addPropertyChangeListener(Player.PROPERTY_score, evt -> {
            for (int i = vBox.getChildren().size() - 1; i >= Math.max(vBox.getChildren().size() - player.getScore(), 0); i--) {
                vBox.getChildren().get(i).setVisible(true);
            }
            for (int i = vBox.getChildren().size() - player.getScore() - 1; i >=0; i--) {
                vBox.getChildren().get(i).setVisible(false);
            }
        });
    }
}
