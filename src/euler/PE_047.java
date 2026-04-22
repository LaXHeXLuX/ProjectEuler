package euler;

public class PE_047 {
    private static int[] pfCounts;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 4;
        int m = 4;
        long result = firstNConsecutiveNumbersToHaveMDistinctPrimeFactors(n, m);
        return String.valueOf(result);
    }

    private static long firstNConsecutiveNumbersToHaveMDistinctPrimeFactors(int n, int m) {
        int limit = 100;
        while (true) {
            limit *= 2;
            primeFactorCounts(limit);
            int count = 0;
            for (int i = 0; i < pfCounts.length; i++) {
                if (pfCounts[i] == m) {
                    if (++count == n) return i-n+1;
                }
                else count = 0;
            }

        }
    }

    private static void primeFactorCounts(int limit) {
        pfCounts = new int[limit];
        pfCounts[0] = 0;
        pfCounts[1] = 1;

        for (int i = 2; i < limit; i+=2) {
            pfCounts[i]++;
        }

        for (int i = 3; i < limit; i+=2) {
            if (pfCounts[i] > 0) continue;
            int k = i;
            while (k >= 0 && k < limit) {
                pfCounts[k]++;
                k += i;
            }
        }
    }
}
