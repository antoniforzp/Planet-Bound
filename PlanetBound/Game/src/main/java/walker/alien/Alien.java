package walker.alien;

import walker.Walker;

public abstract class Alien extends Walker {

    protected int attackUp;
    protected int attackDown;

    protected int deathUp;
    protected int deathDown;

    public boolean checkAttack(int chance) {
        return chance <= attackUp && chance >= attackDown;
    }

    public boolean checkDeath(int chance) {
        return chance <= deathUp && chance >= deathDown;
    }

    public int getAttackUp() {
        return attackUp;
    }

    public int getAttackDown() {
        return attackDown;
    }

    public int getDeathUp() {
        return deathUp;
    }

    public int getDeathDown() {
        return deathDown;
    }
}
