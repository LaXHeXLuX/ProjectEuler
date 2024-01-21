public class PE_073 {
    public static void main(String[] args) {
        int[] fraction1 = {1, 3};
        int[] fraction2 = {1, 2};
        int limitD = 12_000;
        System.out.println(findNumberOfFractionsBetween(fraction1, fraction2, limitD));
    }

    private static int findNumberOfFractionsBetween(int[] fraction1, int[] fraction2, int limitD) {
        int counter = 0;
        for (int d = 2; d <= limitD; d++) {
            int n1 = fraction1[0]*d/fraction1[1] + 1;
            if (compare(new int[] {n1, d}, fraction2) >= 0) continue;
            int n2 = fraction2[0]*d/fraction2[1];
            if (compare(new int[] {n2, d}, fraction2) == 0) {
                n2--;
                if (compare(new int[] {n2, d}, fraction1) <= 0) continue;
            }
            for (int i = n1; i <= n2; i++) {
                if (Divisors.greatestCommonDivisor(d, i) == 1) counter++;
            }
        }
        return counter;
    }

    private static int compare(int[] fraction1, int[] fraction2) {
        int gcd = (int) Divisors.greatestCommonDivisor(fraction1[1], fraction2[1]);
        int numerator1 = fraction1[0] * (fraction2[1]/gcd);
        int numerator2 = fraction2[0] * (fraction1[1]/gcd);
        return Integer.compare(numerator1, numerator2);
    }
}
