package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PE_122 {
    private static int[] smallestExpCounts;

    private static int lowerBound;
    private static int upperBound;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int sum = 0;
        int limit = 100;
        smallestExpCounts(limit);
        for (int i = limit; i > 0; i--) {
            int smallestCount = efficientExpCount(i);
            System.out.println(i + ": " + smallestCount);
            sum += smallestCount-1;
        }
//        makeSmallestExpCounts(limit);
//        for (int i = 0; i < smallestExpCounts.length; i++) {
//            int smallestExpCount = efficientExpCount(i);
//            System.out.println(i + ": " + smallestExpCounts[i]);
//            sum += smallestExpCounts[i];
//        }
        return sum;
    }

    private static void makeSmallestExpCounts(int limit) {
        smallestExpCounts = new int[limit+1];
        Arrays.fill(smallestExpCounts, Integer.MAX_VALUE);
        smallestExpCounts[0] = 0;
        smallestExpCounts[1] = 0;
        smallestExpCounts[2] = 1;
        for (int i = 3; i <= limit; i++) {
            int upperBoundForI = upperBoundForCount(i);
            if (upperBoundForI > upperBound) upperBound = upperBoundForI;
        }

        makeSmallestExpCounts(limit, new ArrayList<>(List.of(1, 2)));
    }

    private static void makeSmallestExpCounts(int limit, List<Integer> powers) {
        if (powers.getLast() > limit || powers.size()-1 > upperBound) return;
        if (smallestExpCounts[powers.getLast()] > powers.size()-1) smallestExpCounts[powers.getLast()] = powers.size()-1;

        List<Integer> powersCopy = new ArrayList<>(powers);
        for (Integer power : powersCopy) {
            powers.add(powers.getLast() + power);
            makeSmallestExpCounts(limit, powers);
            powers.removeLast();
        }
    }

    private static void smallestExpCounts(int limit) {
        smallestExpCounts = new int[limit+1];
        for (int n = 1; n <= limit; n++) {
            smallestExpCounts[n] = upperBoundForCount(n) + 1;
            int binaryCount = binaryExpCount(n) + 1;
            if (binaryCount < smallestExpCounts[n]) smallestExpCounts[n] = binaryCount;
            int pfCount = efficientExpCountPf(n) + 1;
            if (pfCount < smallestExpCounts[n]) smallestExpCounts[n] = pfCount;
        }
    }

    private static int lowerBoundForCount(int n) {
        return (int) Math.ceil(Math.log(n * (countOnes(n)+1)) / Math.log(2) - 2.13);
    }

    private static int upperBoundForCount(int n) {
        return (int) Math.floor(Math.log(n) / Math.log(2) + countOnes(n) - 1);
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
        upperBound = smallestExpCounts[n];
        lowerBound = lowerBoundForCount(n) + 1;
        if (upperBound <= lowerBound) return lowerBound;
        return efficientExpCount(n-2, new ArrayList<>(List.of(1, 2)));
    }

    private static int efficientExpCount(int n, List<Integer> powers) {
        if (n == 0) return powers.size();
        if (n < 0 || powers.size() >= upperBound) return Integer.MAX_VALUE;
        if (n < powers.getLast()) {
            int index = Collections.binarySearch(powers, n);
            if (index > 0) return powers.size() + 1;
            return Integer.MAX_VALUE;
        }

        int smallestCount = upperBound;
        for (Integer power : powers) {
            if (power > n) break;
            List<Integer> newPowers = new ArrayList<>(powers);
            int newPower = powers.getLast() + power;
            newPowers.add(newPower);
            int resultCount = efficientExpCount(n - power, newPowers);
            if (resultCount < smallestCount) {
                smallestCount = resultCount;
                if (smallestCount == lowerBound) {
                    break;
                }
            }
        }
        upperBound = smallestCount;
        return smallestCount;
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
