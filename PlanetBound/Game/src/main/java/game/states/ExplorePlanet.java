package game.states;

import config.Logger;
import game.*;
import game.singletons.Data;
import logic.ExplorePlanetLogic;

public class ExplorePlanet extends State {

    private final ExplorePlanetLogic logic;

    public ExplorePlanet() {
        this.logic = new ExplorePlanetLogic();
    }

    public ExplorePlanetLogic getLogic() {
        return logic;
    }

    @Override
    public State move(int x, int y) {

        Data.getInstance().setFinishedExploring(logic.move(x, y));

        if (Data.getInstance().isFinishedExploring()) {
            Logger.log("Extraction point reached. Returning to ship");
            return new Multiply();
        }

        if (Data.getInstance().isAlienMet()) {
            Logger.log("Alien encountered: " + Data.getInstance().getAlien().getClass().getSimpleName());
            return new Fight();
        }

        Logger.log("Drone travels on surface: " + Data.getInstance().getShip().getDrone().getPosition());
        return new ExplorePlanet();
    }
}
