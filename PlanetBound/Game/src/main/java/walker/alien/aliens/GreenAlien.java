package walker.alien.aliens;

import walker.alien.Alien;

public class GreenAlien extends Alien {

    public GreenAlien() {
        this.attackDown = 1;
        this.attackUp = 2;

        this.deathDown = 4;
        this.deathUp = 6;
    }
}
