package game.states;

import exceptions.WrongArgumentException;
import config.Logger;
import game.State;
import game.singletons.Data;
import logic.UpgradeLogic;

public class Upgrade extends State {

    private final UpgradeLogic logic;

    public Upgrade() {
        this.logic = new UpgradeLogic();
    }

    @Override
    public State upgrade(int choice) throws WrongArgumentException {

        boolean check;
        switch (choice) {
            case 1: {
                check = logic.refillMiningDrone();
                if (check) {
                    Logger.log("refilled mining drone's shields");
                }
            }
            break;
            case 2: {
                check = logic.upgradeCargoHold();
                if (check) {
                    Logger.log("Cargo hold upgraded");
                }
            }
            break;
            case 3: {
                check = logic.hireNewMember();
                if (check) {
                    Logger.log("Hired new member");
                }
            }
            break;
            case 4: {
                check = logic.upgradeWeaponSystem();
                if (check) {
                    Logger.log("Weapon system upgraded");
                }
            }
            break;
            case 5: {
                check = logic.buyNewMiningDrone();
                if (check) {
                    Logger.log("New mining drone bought");
                }
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }

        Data.getInstance().setCanUpgrade(check);
        return new Upgrade();
    }

    @Override
    public State finish() {

        Logger.log("Returned to travelling in space");
        return new WaitInSpace();
    }
}
