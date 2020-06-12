package game.states;

import config.Logger;
import game.State;

public class GameOver extends State {

    @Override
    public State finish() {

        Logger.log("Exit game");
        System.exit(0);
        return null;
    }
}
