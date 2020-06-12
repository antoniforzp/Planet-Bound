import config.Loader;
import game.Game;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import walker.alien.aliens.testing.BadassAlien;


class TestTemp {

    @Test
    void t() {

        Data.getInstance().setAlien(new BadassAlien());
        Game.getInstance().chooseShip(1);
        Game.getInstance().travel();

        Data.getInstance().positionProperty().addListener((oldVal, newVal) -> {
            System.out.println("dupadupaudpa");
        });

        Loader.saveGame();


        Loader.loadGame();

        Data.getInstance().positionProperty().addListener((oldVal, newVal) -> {
            System.out.println("dupadupaudpa2" + newVal);
        });

        Data.getInstance().setPosition(100);
    }
}