package walker.alien.aliens;

import walker.alien.Alien;

public class BlackAlien extends Alien {

    public BlackAlien() {
        this.attackDown = 0;
        this.attackUp = 1;

        this.deathDown = 5;
        this.deathUp = 6;
    }
}
