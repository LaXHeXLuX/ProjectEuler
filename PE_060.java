import util.ArrayFunctions;
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
        System.out.println(PE());
    }

    public static long PE() {
        int size = 5;
        Set<Integer> set = nthPrimeSet(size);
        return sum(set);
    }

    private static int sum(Set<Integer> set) {
        int sum = 0;
        for (int a : set) sum += a;
        return sum;
    }

    private static Set<Integer> nthPrimeSet(int n) {
        if (n < 2) throw new RuntimeException("n (" + n + ") can't be smaller than 2!");
        primes = Primes.sieveOfPrimes(1_000_000);
        primesInt = Converter.booleanArrToIntArr(primes);
        int biggestPrimeLimit = primesInt[primesInt.length-1];

        for (int prime : primesInt) {
            if (prime > biggestPrimeLimit) break;
            if (!primePairs.containsKey(prime)) primePairs.put(prime, primePairSetFor(prime));
            Set<Set<Integer>> workingPrimeSets = primeSet(n-1, primePairs.get(prime));
            for (Set<Integer> workingPrimeSet : workingPrimeSets) {
                workingPrimeSet.add(prime);
            }
            for (Set<Integer> workingPrimeSet : workingPrimeSets) {
                int sum = sum(workingPrimeSet);
                if (lowestSumPrimeSet == null || lowestSum > sum) {
                    lowestSumPrimeSet = workingPrimeSet;
                    lowestSum = sum;
                    if (biggestPrimeLimit > sum) {
                        biggestPrimeLimit = sum;
                    }
                }
            }
        }

        return lowestSumPrimeSet;
    }

    private static Set<Set<Integer>> primeSet(int size, Set<Integer> primePairSet) {
        Set<Set<Integer>> validPrimeSets = new HashSet<>();
        if (size == 1) {
            for (Integer prime : primePairSet) {
                validPrimeSets.add(new TreeSet<>(Collections.singleton(prime)));
            }
            return validPrimeSets;
        }
        if (size > primePairSet.size()) return validPrimeSets;
        if (sum(primePairSet) > lowestSum) return validPrimeSets;

        for (Integer i : primePairSet) {
            TreeSet<Integer> union = new TreeSet<>(primePairs.get(i));
            union.retainAll(primePairSet);
            Set<Set<Integer>> nextValidSets = primeSet(size-1, union);
            for (Set<Integer> nextValidSet : nextValidSets) {
                Set<Integer> validSet = new TreeSet<>(nextValidSet);
                validSet.add(i);
                validPrimeSets.add(validSet);
            }
        }

        return validPrimeSets;
    }

    private static TreeSet<Integer> primePairSetFor(int p1) {
        TreeSet<Integer> primePairSet = new TreeSet<>();
        for (int p2 : primesInt) {
            if (p2 >= p1) break;
            if (isPrimePair(p1, p2)) primePairSet.add(p2);
        }
        return primePairSet;
    }

    private static boolean isPrimePair(int p1, int p2) {
        int[] digits1 = Converter.digitArray(p1);
        int[] digits2 = Converter.digitArray(p2);
        long p12 = Converter.fromDigitArray(ArrayFunctions.concatenate(digits1, digits2));
        long p21 = Converter.fromDigitArray(ArrayFunctions.concatenate(digits2, digits1));

        return isPrime(p12) && isPrime(p21);
    }

    private static boolean isPrime(long n) {
        if (n < primes.length && n >= 0) return primes[Math.toIntExact(n)];
        return Primes.isPrime(n);
    }
}
