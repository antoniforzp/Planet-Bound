package dice;

import java.util.Random;

public class Dice6 {
    private static final Random random = new Random();
    private static final int min = 1;
    private static final int max = 6;

    static public int roll() {
        int number = random.nextInt((max - min) + 1) + min;
        System.out.println("Dice6 rolled: " + number);
        return number;
    }
}
