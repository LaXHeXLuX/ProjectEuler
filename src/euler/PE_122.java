package euler;

import utils.Diophantine;
import utils.Primes;

public class PE_122 {
    private static Primes.PF[][] pfSieve;
    private static int[] smallestExpCounts;
    private static int[] currentChain;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int limit = 200;
        makeInitialSmallestExpCounts(limit);
        makeSmallestExpCounts(limit);
        int sum = 0;
        for (int smallestExpCount : smallestExpCounts) sum += smallestExpCount-1;
        return String.valueOf(sum);
    }

    private static void makeInitialSmallestExpCounts(int limit) {
        pfSieve = Primes.primeFactorSieve(limit+1);

        smallestExpCounts = new int[limit+1];
        smallestExpCounts[0] = 1;
        for (int n = 1; n <= limit; n++) {
            smallestExpCounts[n] = binaryExpCount(n)+1;
        }
        for (int n = 4; n <= limit; n++) {
            int pfCount = efficientExpCountPf(n)+1;
            if (pfCount < smallestExpCounts[n]) {
                smallestExpCounts[n] = pfCount;
            }
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
        if (lastIndex == currentChain.length-1) return;
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

    private static int efficientExpCountPf(int n) {
        int expSum = 0;
        for (Primes.PF pf : pfSieve[n]) {
            expSum += smallestExpCounts[(int) Diophantine.pow(pf.primeFactor, pf.power)] - 1;
        }
        return expSum;
    }

    private static int binaryExpCount(int n) {
        return 32 - Integer.numberOfLeadingZeros(n) + Integer.bitCount(n) - 2;
    }
}