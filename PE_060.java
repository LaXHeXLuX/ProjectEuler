import util.Converter;
import util.Primes;

import java.util.*;

public class PE_060 {
    private static boolean[] primes;
    private static int[] primesInt;
    private static Set<Integer> lowestSumPrimeSet;
    private static int lowestSum = Integer.MAX_VALUE;
    private static final Map<Integer, Set<Integer>> primePairs = new HashMap<>();

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int size = 5;
        nthPrimeSet(size);
        return sum(lowestSumPrimeSet);
    }

    private static int sum(Set<Integer> set) {
        int sum = 0;
        for (int a : set) sum += a;
        return sum;
    }

    private static void nthPrimeSet(int n) {
        if (n < 2) throw new RuntimeException("n (" + n + ") can't be smaller than 2!");
        primes = Primes.sieveOfPrimes(100_000_000);
        primesInt = Converter.booleanArrToIntArr(primes);
        for (int prime : primesInt) {
            if (prime > lowestSum) break;
            primePairs.put(prime, primePairSetFor(prime));
            Set<Integer> currentPrimePairs = primePairs.get(prime);

            Set<Set<Integer>> workingPrimeSets = primeSet(n-1, currentPrimePairs);
            for (Set<Integer> workingPrimeSet : workingPrimeSets) {
                workingPrimeSet.add(prime);
            }
            for (Set<Integer> workingPrimeSet : workingPrimeSets) {
                int sum = sum(workingPrimeSet);
                if (lowestSumPrimeSet == null || lowestSum > sum) {
                    lowestSumPrimeSet = workingPrimeSet;
                    lowestSum = sum;
                }
            }
        }
    }

    private static Set<Set<Integer>> primeSet(int size, Set<Integer> primePairSet) {
        Set<Set<Integer>> validPrimeSets = new HashSet<>();
        if (size == 0) return validPrimeSets;
        if (size == 1) {
            for (Integer prime : primePairSet) {
                validPrimeSets.add(Set.of(prime));
            }
            return validPrimeSets;
        }
        if (size > primePairSet.size()) return validPrimeSets;

        for (Integer i : primePairSet) {
            Set<Integer> union = new HashSet<>(primePairs.get(i));
            union.retainAll(primePairSet);
            Set<Set<Integer>> nextValidSets = primeSet(size-1, union);
            for (Set<Integer> nextValidSet : nextValidSets) {
                Set<Integer> validSet = new HashSet<>(nextValidSet);
                validSet.add(i);
                validPrimeSets.add(validSet);
            }
        }

        return validPrimeSets;
    }

    private static Set<Integer> primePairSetFor(int p1) {
        Set<Integer> primePairSet = new HashSet<>();
        for (int p2 : primesInt) {
            if (p2 >= p1) break;
            if (isPrimePair(p1, p2)) primePairSet.add(p2);
        }
        return primePairSet;
    }

    private static boolean isPrimePair(int p1, int p2) {
        String p1s = String.valueOf(p1);
        String p2s = String.valueOf(p2);
        long p12 = Long.parseLong(p1s + p2s);
        long p21 = Long.parseLong(p2s + p1s);

        return isPrime(p12) && isPrime(p21);
    }

    private static boolean isPrime(long n) {
        if (n < primes.length && n >= 0) return primes[Math.toIntExact(n)];
        return Primes.isPrime(n);
    }
}
