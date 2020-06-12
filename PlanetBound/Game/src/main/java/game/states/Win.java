package game.states;

import config.Logger;
import game.State;

public class Win extends State {

    @Override
    public State finish() {

        Logger.log("Game exit");
        System.exit(0);
        return null;
    }
}
