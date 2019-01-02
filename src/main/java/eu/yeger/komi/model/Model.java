package eu.yeger.komi.model;

public final class Model {

    private static Model instance;

    private Game game;

    private Model() {
        game = new Game();
    }

    public static void resetModel() {
        instance = new Model();
    }

    public static Model getInstance() {
        if (instance == null) instance = new Model();
        return instance;
    }

    public Game getGame() {
        return game;
    }

}
