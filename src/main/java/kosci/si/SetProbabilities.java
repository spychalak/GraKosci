package kosci.si;

import java.lang.Math;

public class SetProbabilities {
    private static int factorial(int number) {
        if (number < 1) {
            return 1;
        }
        else {
            return number * factorial(number - 1);
        }
    }

    public static double threeOfAKind(int m, int r) {
        int factorialR = factorial(r);
        double firstCase = factorialR / (factorial(m) * factorial(r - m)) * Math.pow(5, r-m);
        double secondCase = factorialR / (factorial(m + 1) * factorial(r - (m + 1))) * Math.pow(5, r - (m + 1));
        return (firstCase + secondCase + 1) / Math.pow(6, r);
    }

    public static double fourOfAKind(int m, int r) {
        double numerator = factorial(r) / (factorial(m) * factorial(r - m)) * Math.pow(5, r-m) + 1;
        return numerator / Math.pow(6, r);
    }

    public static double fullHouse(int r, int n) {
        double numerator = factorial(r) / (factorial(n) * factorial(r - n));
        return numerator / Math.pow(6, r);
    }

    public static double smallStraight(int m, int r) {
        int factorialR = factorial(r);
        double firstCase = factorialR * (6 - m);
        double secondCase = factorialR * m / 2;
        return (firstCase + secondCase) / Math.pow(6, r);
    }

    public static double bigStraight(int r) {
        return factorial(r) / Math.pow(6, r);
    }

    public static double general(int r) {

        return Math.pow(1.0/6.0, r);
    }
}
