package eu.yeger.komi.model;

import org.fulib.Fulib;
import org.fulib.builder.ClassBuilder;
import org.fulib.builder.ClassModelBuilder;
import org.fulib.classmodel.ClassModel;

import java.util.LinkedHashSet;

public class GenModel {

    public static void main(String[] args) {
        //preparation
        ClassModelBuilder mb = Fulib.classModelBuilder("eu.yeger.comaga.model", "src/main/java");
        mb.setJavaFXPropertyStyle().setDefaultCollectionClass(LinkedHashSet.class);


        //classes
        ClassBuilder game = mb.buildClass("Game");

        ClassBuilder player = mb.buildClass("Player");

        ClassBuilder slot = mb.buildClass("Slot");

        ClassBuilder pawn = mb.buildClass("Pawn");

        ClassBuilder group = mb.buildClass("Group");


        //attributes
        player.buildAttribute("color", ClassModelBuilder.STRING);
        player.buildAttribute("score", ClassModelBuilder.INT);

        pawn.buildAttribute("xPos", ClassModelBuilder.INT);
        pawn.buildAttribute("yPos", ClassModelBuilder.INT);


        //associations
        game.buildAssociation(player, "players", 2, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "currentPlayer", ClassModelBuilder.ONE, "currentGame", ClassModelBuilder.ONE);

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
