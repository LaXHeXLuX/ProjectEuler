package euler;

import utils.Primes;

public class PE_122 {
    private static int[] smallestExpCounts;
    private static int[] currentChain;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 200;
        makeInitialSmallestExpCounts(limit);
        makeSmallestExpCounts(limit);
        int sum = 0;
        for (int smallestExpCount : smallestExpCounts) sum += smallestExpCount-1;
        return sum;
    }

    private static void makeInitialSmallestExpCounts(int limit) {
        smallestExpCounts = new int[limit+1];
        smallestExpCounts[0] = 1;
        for (int i = 1; i <= 3; i++) smallestExpCounts[i] = i;
        for (int n = 4; n <= limit; n++) {
            smallestExpCounts[n] = upperBound(n);
            if (smallestExpCounts[n] > smallestExpCounts[n-1] + 1) {
                smallestExpCounts[n] = smallestExpCounts[n-1] + 1;
            }
            if (smallestExpCounts[n] > smallestExpCounts[n-2] + 1) {
                smallestExpCounts[n] = smallestExpCounts[n-2] + 1;
            }
        }
    }

    private static void makeSmallestExpCounts(int limit) {
        int maxArrSize = 0;
        for (int smallestExpCount : smallestExpCounts) {
            if (smallestExpCount > maxArrSize) maxArrSize = smallestExpCount;
        }
        currentChain = new int[maxArrSize];
        currentChain[0] = 1;
        currentChain[1] = 2;
        makeSmallestExpCounts(limit, 1);
    }

    private static void makeSmallestExpCounts(int limit, int lastIndex) {
        int lastElement = currentChain[lastIndex];
        if (lastIndex+1 == currentChain.length) return;
        for (int i = lastIndex; i >= 0; i--) {
            int element = currentChain[i];
            int newElement = element + lastElement;
            if (newElement > limit) continue;
            if (lastIndex+1 > smallestExpCounts[element + lastElement]) continue;
            currentChain[lastIndex+1] = newElement;
            if (smallestExpCounts[newElement] > lastIndex+2) {
                smallestExpCounts[newElement] = lastIndex+2;
            }
            makeSmallestExpCounts(limit, lastIndex+1);
        }
    }

    private static int power(int n, int pow) {
        int prod = n;
        for (int i = 1; i < pow; i++) {
            prod *= n;
        }
        return prod;
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
            x &= (x - 1);
            c++;
        }
        return c;
    }
}