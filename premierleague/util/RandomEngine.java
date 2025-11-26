package premierleague.util;

import java.util.Random;

/**
 * 랜덤 유틸리티
 * - getInt(min, max)
 * - getDouble() -> 0.0 ~ 1.0
 * - chance(percent) -> true/false
 */
public class RandomEngine {

    private static final Random rnd = new Random();

    public static int getInt(int min, int max) {
        if (min >= max) return min;
        return rnd.nextInt(max - min + 1) + min;
    }

    public static double getDouble() {
        return rnd.nextDouble();
    }

    public static boolean chance(int percent) {
        return rnd.nextInt(100) < percent;
    }
}
