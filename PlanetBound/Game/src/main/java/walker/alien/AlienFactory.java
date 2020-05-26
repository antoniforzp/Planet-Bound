package walker.alien;

import walker.alien.aliens.BlackAlien;
import walker.alien.aliens.BlueAlien;
import walker.alien.aliens.GreenAlien;
import walker.alien.aliens.RedAlien;

import java.util.Random;

public class AlienFactory {

    static public Alien getRandomAlien() {

        Random random = new Random();
        switch (random.nextInt(3)) {

            case 0: {
                return new BlackAlien();
            }
            case 1: {
                return new RedAlien();
            }
            case 2: {
                return new GreenAlien();
            }
            case 3: {
                return new BlueAlien();
            }
            default: {
                return null;
            }
        }
    }
}
