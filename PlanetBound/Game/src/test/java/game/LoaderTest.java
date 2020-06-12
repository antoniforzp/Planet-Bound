package game;

import config.Loader;
import game.singletons.Data;
import game.states.ChooseShip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LoaderTest {

    @Test
    void saveGame() {

        Game.getInstance().setState(new ChooseShip());
        Game.getInstance().chooseShip(1);

        Data data = Data.getInstance();
        Loader.saveGame();


    }

    @Test
    void loadGame() {
    }
}