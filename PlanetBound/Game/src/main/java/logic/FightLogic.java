package logic;

import dice.Dice6;
import config.Logger;
import game.singletons.Data;
import walker.alien.Alien;
import walker.miningDrone.MiningDrone;

import java.io.Serializable;

public class FightLogic implements Serializable {

    public boolean fight(MiningDrone drone, Alien alien) {

        StringBuilder strB = new StringBuilder();
        strB.append("Alien type: ").append(alien.getClass().getSimpleName()).append("\n");
        do {
            if (alien.checkAttack(Dice6.roll())) {
                if (!drone.takeDamage(1)) {
                    strB.append("(!)Drone destroyed\n");
                    return false;
                }
                strB.append("(!)Shield damaged: ").append(drone.getShields()).append("/").append(drone.getShieldsCapacity()).append("\n");
            }
        } while (!alien.checkDeath(Dice6.roll()));
        strB.append("Alien is dead\n");

        int killed = Data.getInstance().getAliensKilled();
        Data.getInstance().setAliensKilled(++killed);

        Data.getInstance().setFightLog(strB.toString());
        Logger.log("Fight process: \n " + strB.toString());
        return true;
    }
}
