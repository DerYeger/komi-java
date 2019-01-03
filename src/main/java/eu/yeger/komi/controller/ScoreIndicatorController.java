package eu.yeger.komi.controller;

import eu.yeger.komi.model.Model;
import eu.yeger.komi.model.Player;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScoreIndicatorController {

    private static final Color DEFAULT_COLOR = Color.web("#666666");

    private VBox vBox;

    public void initialize(VBox vBox, Player player) {
        this.vBox = vBox;
//        for (int i = 0; i < vBox.getChildren().size(); i++) {
//            ((Circle) vBox.getChildren().get(i)).setFill(Color.RED);
//        }
//        player.addPropertyChangeListener(Player.PROPERTY_score, evt -> {
//            for (int i = vBox.getChildren().size() - 1; i >= Math.max(vBox.getChildren().size() - player.getScore(), 0); i--) {
//                ((Circle) vBox.getChildren().get(i)).setFill(Model.getInstance().getGame().getPlayers().get(0).equals(player) ? Color.WHITE : Color.BLACK);
//                //vBox.getChildren().get(i).setVisible(true);
//            }
//            for (int i = vBox.getChildren().size() - player.getScore() - 1; i >=0; i--) {
//                ((Circle) vBox.getChildren().get(i)).setFill(Color.RED);
//                //vBox.getChildren().get(i).setVisible(false);
//            }
//        });
        setColor(DEFAULT_COLOR, 0, vBox.getChildren().size());
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
