package logic;

import dice.Dice6;
import logic.singleton.LogicConfig;
import walker.alien.Alien;
import walker.miningDrone.MiningDrone;

public class FightLogic {

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

        int killed = LogicConfig.getInstance().getAliensKilled();
        LogicConfig.getInstance().setAliensKilled(++killed);

        LogicConfig.getInstance().setFightLog(strB.toString());
        return true;
    }
}
