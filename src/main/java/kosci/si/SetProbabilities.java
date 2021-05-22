package kosci.si;

import java.lang.Math;

public class SetProbabilities {
    private static int factorial(int number) {
		int ret = 1;
		for(;number>1; --number)
			ret *= number;
		return ret;
    }
	
	public static double newton(int u, int d) {
		return factorial(u) / (factorial(d) * factorial(u-d));
	}

    public static double threeOfAKind(int m, int r) {
        double firstCase = newton(r, m) * Math.pow(5, r-m);
        double secondCase = newton(r, m+1) * Math.pow(5, r - (m + 1));
        return (firstCase + secondCase + 1.0) / Math.pow(6, r);
    }

    public static double fourOfAKind(int m, int r) {
        double numerator = newton(r, m) * Math.pow(5, r-m) + 1.0;
        return numerator / Math.pow(6, r);
    }

    public static double fullHouse(int r, int n) {
        return newton(r, n) / Math.pow(6, r);
    }

    public static double smallStraight(int m, int r) {
        int factorialR = factorial(r);
        double firstCase = factorialR * (6 - m);
        double secondCase = (double)(factorialR * m) / 2.0;
        return (firstCase + secondCase) / Math.pow(6, r);
    }

    public static double bigStraight(int r) {
        return factorial(r) / Math.pow(6, r);
    }

    public static double general(int r) {
        return Math.pow(1.0/6.0, r);
    }
}
