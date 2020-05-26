package walker.alien.aliens.testing;

import walker.alien.Alien;

public class WeakAlien extends Alien {
    public WeakAlien() {
        this.attackDown = 0;
        this.attackUp = 0;

        this.deathDown = 1;
        this.deathUp = 6;
    }
}
