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

        ClassBuilder group = mb.buildClass("Group");


        //attributes
        board.buildAttribute("size", ClassModelBuilder.INT);

        player.buildAttribute("color", ClassModelBuilder.STRING);
        player.buildAttribute("score", ClassModelBuilder.INT);

        slot.buildAttribute("xPos", ClassModelBuilder.INT);
        slot.buildAttribute("yPos", ClassModelBuilder.INT);


        //associations
        game.buildAssociation(board, "board", ClassModelBuilder.ONE, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "players", ClassModelBuilder.MANY, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "currentPlayer", ClassModelBuilder.ONE, "currentGame", ClassModelBuilder.ONE);

        board.buildAssociation(slot, "slots", ClassModelBuilder.MANY, "board", ClassModelBuilder.ONE);

        player.buildAssociation(pawn, "pawns", ClassModelBuilder.MANY, "player", ClassModelBuilder.ONE);
        player.buildAssociation(group, "groups", ClassModelBuilder.MANY, "player", ClassModelBuilder.ONE);

        slot.buildAssociation(pawn, "pawn", ClassModelBuilder.ONE, "slot", ClassModelBuilder.ONE);

        pawn.buildAssociation(group, "group", ClassModelBuilder.ONE, "pawns", ClassModelBuilder.MANY);

        group.buildAssociation(slot, "liberties", ClassModelBuilder.MANY, "groups", ClassModelBuilder.MANY);

        //generator
        ClassModel model = mb.getClassModel();
        Fulib.generator().generate(model);
    }
}
