package euler;

import utils.Primes;

public class PE_122 {
    private static int[] smallestExpCounts;
    private static int[] currentChain;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int sum = 0;
        int limit = 200;
        smallestExpCounts = smallestExpCounts(limit);
        for (int i = limit; i > 0; i--) {
            int smallestCount = efficientExpCount(i);
            //System.out.println(i + ": " + smallestCount + " - " + smallestCounts2[i]);
            sum += smallestCount-1;
        }
        return sum;
    }

    private static int[] smallestExpCounts(int limit) {
        int[] smallestExpCounts = new int[limit+1];
        smallestExpCounts[0] = 0;
        smallestExpCounts[1] = 1;
        smallestExpCounts[2] = 2;
        smallestExpCounts[3] = 3;
        for (int n = 4; n <= limit; n++) {
            smallestExpCounts[n] = upperBound(n);
            if (smallestExpCounts[n] > smallestExpCounts[n-1] + 1) {
                smallestExpCounts[n] = smallestExpCounts[n-1] + 1;
            }
            if (smallestExpCounts[n] > smallestExpCounts[n-2] + 1) {
                smallestExpCounts[n] = smallestExpCounts[n-2] + 1;
            }
        }
        return smallestExpCounts;
    }

    private static int power(int n, int pow) {
        int prod = n;
        for (int i = 1; i < pow; i++) {
            prod *= n;
        }
        return prod;
    }

    private static int efficientExpCount(int n) {
        if (n == 1 || n == 2) return n;
        if (n == 3 || n == 4) return 3;
        currentChain = new int[smallestExpCounts[n]];
        currentChain[0] = 1;
        currentChain[1] = 2;
        return efficientExpCount(n, 1);
    }

    private static int efficientExpCount(int n, int lastIndex) {
        if (lastIndex+1 == smallestExpCounts[n]) return smallestExpCounts[n];
        int lastPower = currentChain[lastIndex];
        if (lastPower == n) return lastIndex+1;

        int smallestCount = smallestExpCounts[n];
        int remaining = n - lastPower;
        for (int i = 0; i <= lastIndex; i++) {
            int power = currentChain[i];
            if (power > remaining) break;
            int newPower = lastPower + power;
            currentChain[lastIndex+1] = newPower;
            int resultCount = efficientExpCount(n, lastIndex+1);
            if (resultCount < smallestCount) smallestCount = resultCount;
        }
        smallestExpCounts[n] = smallestCount;
        return smallestCount;
    }

    private static int upperBound(int n) {
        int bound = binaryExpCount(n) + 1;
        int pfCount = efficientExpCountPf(n) + 1;
        if (pfCount < bound) bound = pfCount;
        return bound;
    }

    private static int efficientExpCountPf(int n) {
        Primes.PF[] pfs = Primes.primeFactors(n);
        int expSum = 0;
        for (Primes.PF pf : pfs) {
            expSum += binaryExpCount(power((int) pf.primeFactor, pf.power));
        }
        return expSum;
    }

    private static int binaryExpCount(int n) {
        return 32 - Integer.numberOfLeadingZeros(n) + countOnes(n) - 2;
    }

    private static int countOnes(int x) {
        int c = 0;
        while (x != 0) {
            x &= (x - 1);  // clears the lowest-set bit
            c++;
        }
        return c;
    }
}
