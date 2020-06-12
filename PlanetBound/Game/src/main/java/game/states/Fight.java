package game.states;

import config.Logger;
import game.singletons.Data;
import game.State;
import logic.FightLogic;
import walker.alien.Alien;
import walker.miningDrone.MiningDrone;

public class Fight extends State {

    private final FightLogic logic;

    public Fight() {
        this.logic = new FightLogic();
    }

    //USABILITY
    @Override
    public State finish() {
        return new ExplorePlanet();
    }

    @Override
    public State fight() {

        MiningDrone drone = Data.getInstance().getShip().getDrone();
        Alien alien = Data.getInstance().getAlien();

        Data.getInstance().setFightWon(logic.fight(drone, alien));
        if (Data.getInstance().isFightWon()) {

            Logger.log("Fight won. Lost shields: " + (Data.getInstance().getShip().getDrone().getShieldsCapacity() -
                    Data.getInstance().getShip().getDrone().getShields()));
            return new ExplorePlanet();
        }

        Logger.log("Drone destroyed");
        return new WaitInSpace();
    }
}
