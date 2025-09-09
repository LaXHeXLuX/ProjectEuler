import util.Divisors;

public class PE_071 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limitD = 1_000_000;
        int[] fraction = {3, 7};
        return findFractionBefore(limitD, fraction)[0];
    }

    private static int numerator(int[] fraction, int d) {
        if (fraction[1] == d) return fraction[0]-1;
        return fraction[0]*d/fraction[1];
    }

    private static int[] findFractionBefore(int limitD, int[] fraction) {
        int[] winningFraction = {0, 1};
        for (int d = 2; d <= limitD; d++) {
            if (d % fraction[1] == 0) continue;
            int[] currentFraction = {numerator(fraction, d), d};

            if (compare(currentFraction, winningFraction) > 0) winningFraction = currentFraction;
        }
        return winningFraction;
    }

    private static int compare(int[] fraction1, int[] fraction2) {
        int gcd = (int) Divisors.greatestCommonDivisor(fraction1[1], fraction2[1]);
        int numerator1 = fraction1[0] * (fraction2[1]/gcd);
        int numerator2 = fraction2[0] * (fraction1[1]/gcd);
        return Integer.compare(numerator1, numerator2);
    }
}
