package game.states;

import dice.Dice6;
import config.Logger;
import game.State;
import game.singletons.Data;
import logic.MultiplyLogic;

public class Multiply extends State {

    private final MultiplyLogic logic;

    public Multiply() {
        this.logic = new MultiplyLogic();
    }

    @Override
    public State finish() {

        int roll = Dice6.roll();
        Data.getInstance().setWinCondition(logic.multiply(roll));

        if (Data.getInstance().isWinCondition()) {
            Logger.log("Game won. 5 Artefacts has been collected");
            return new Win();
        }

        Logger.log("Multiplying resources:" + Data.getInstance().getResource().getClass().getSimpleName() + " x " + roll);
        return new WaitInSpace();
    }
}
