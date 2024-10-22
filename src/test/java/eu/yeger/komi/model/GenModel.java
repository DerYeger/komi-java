package eu.yeger.komi.model;

import org.fulib.Fulib;
import org.fulib.builder.ClassBuilder;
import org.fulib.builder.ClassModelBuilder;
import org.fulib.classmodel.ClassModel;

public class GenModel {

    public static void main(String[] args) {
        //preparation
        ClassModelBuilder mb = Fulib.classModelBuilder("eu.yeger.komi.model", "src/main/java");


        //classes
        ClassBuilder game = mb.buildClass("Game");

        ClassBuilder board = mb.buildClass("Board");

        ClassBuilder player = mb.buildClass("Player");

        ClassBuilder slot = mb.buildClass("Slot");

        ClassBuilder pawn = mb.buildClass("Pawn");


        //attributes
        game.buildAttribute("round", ClassModelBuilder.INT);
        game.buildAttribute("roundsToWin", ClassModelBuilder.INT);
        game.buildAttribute("scoreToWin", ClassModelBuilder.INT);

        board.buildAttribute("size", ClassModelBuilder.INT);

        player.buildAttribute("score", ClassModelBuilder.INT);
        player.buildAttribute("roundsWon", ClassModelBuilder.INT);

        slot.buildAttribute("xPos", ClassModelBuilder.INT);
        slot.buildAttribute("yPos", ClassModelBuilder.INT);

        pawn.buildAttribute("hasLiberties", ClassModelBuilder.BOOLEAN);


        //associations
        game.buildAssociation(board, "board", ClassModelBuilder.ONE, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "players", ClassModelBuilder.MANY, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "currentPlayer", ClassModelBuilder.ONE, "currentGame", ClassModelBuilder.ONE);
        game.buildAssociation(player, "winner", ClassModelBuilder.ONE, "wonGame", ClassModelBuilder.ONE);

        board.buildAssociation(slot, "slots", ClassModelBuilder.MANY, "board", ClassModelBuilder.ONE);

        player.buildAssociation(pawn, "pawns", ClassModelBuilder.MANY, "player", ClassModelBuilder.ONE);

        slot.buildAssociation(slot, "neighbors", ClassModelBuilder.MANY, "neighbors", ClassModelBuilder.MANY);
        slot.buildAssociation(pawn, "pawn", ClassModelBuilder.ONE, "slot", ClassModelBuilder.ONE);


        //generator
        ClassModel model = mb.getClassModel();
        Fulib.generator().generate(model);
    }
}
