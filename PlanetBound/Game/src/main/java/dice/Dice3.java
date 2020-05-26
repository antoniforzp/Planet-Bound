package dice;

import java.util.Random;

public class Dice3 {
    private static final int min = 1;
    private static final int max = 3;

    static public int roll() {
        Random random = new Random();
        int number = random.nextInt((max - min) + 1) + min;
        System.out.println("Dice3 rolled: " + number);
        return number;
    }
}
