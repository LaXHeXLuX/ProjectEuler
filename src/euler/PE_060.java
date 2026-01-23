package euler;

import utils.Graph;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PE_060 {
    private static final List<Integer> primesInt1 = new ArrayList<>();
    private static final List<Integer> primesInt2 = new ArrayList<>();

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int size = 5;
        Set<Integer> lowestSumPrimeSet = nthPrimeSet(size);
        System.out.println(lowestSumPrimeSet);
        return sum(lowestSumPrimeSet);
    }

    private static int sum(Set<Integer> set) {
        int sum = 0;
        for (int a : set) sum += a;
        return sum;
    }

    private static void makePrimes(int limit) {
        boolean[] composites = new boolean[limit / 2];
        composites[0] = true;
        for (int i = 3; i < limit; i+=2) {
            if (composites[i/2]) continue;
            int prod = i*i;
            while (prod < limit && prod > 0) {
                composites[prod/2] = true;
                prod += 2*i;
            }
            if (i % 3 == 1) primesInt1.add(i);
            else if (i % 3 == 2) primesInt2.add(i);
        }
    }

    private static Set<Integer> nthPrimeSet(int n) {
        if (n < 2) throw new RuntimeException("n (" + n + ") can't be smaller than 2!");
        int lowestSum = Integer.MAX_VALUE;
        Set<Integer> lowestSumPrimeSet = Set.of();
        int limit = 10_000; // help here

        double s = System.currentTimeMillis();
        primesInt1.add(3);
        primesInt2.add(3);
        makePrimes(limit);
        double e = System.currentTimeMillis();
        System.out.println("composites time " + (e-s) + " ms");
        Graph primePairGraph = new Graph();
        for (int prime : primesInt1) {
            if (prime > lowestSum) break;
            List<Integer> primePairSet = primePairSetFor(prime);
            primePairGraph.addNode(prime);
            for (Integer i : primePairSet) {
                primePairGraph.addEdge(prime, i, 1);
                primePairGraph.addEdge(i, prime, 1);
            }
            Set<Integer> clique = primePairGraph.clique(n, prime);
            if (!clique.isEmpty()) {
                int sum = sum(clique);
                if (lowestSum > sum) {
                    lowestSumPrimeSet = clique;
                    lowestSum = sum;
                }
            }
        }
        primePairGraph = new Graph();
        for (int prime : primesInt2) {
            if (prime > lowestSum) break;
            List<Integer> primePairSet = primePairSetFor(prime);
            primePairGraph.addNode(prime);
            for (Integer i : primePairSet) {
                primePairGraph.addEdge(prime, i, 1);
                primePairGraph.addEdge(i, prime, 1);
            }
            Set<Integer> clique = primePairGraph.clique(n, prime);
            if (!clique.isEmpty()) {
                int sum = sum(clique);
                if (lowestSum > sum) {
                    lowestSumPrimeSet = clique;
                    lowestSum = sum;
                }
            }
        }

        return lowestSumPrimeSet;
    }

    private static List<Integer> primePairSetFor(int p1) {
        List<Integer> primePairSet = new ArrayList<>();
        List<Integer> primes;
        if (p1 % 3 == 1) primes = primesInt1;
        else primes = primesInt2;
        for (int p2 : primes) {
            if (p2 >= p1) break;
            if (isPrimePair(p1, p2)) primePairSet.add(p2);
        }
        return primePairSet;
    }

    private static boolean isPrimePair(int p1, int p2) {
        int len1 = (int) Math.log10(p1) + 1;
        int len2 = (int) Math.log10(p2) + 1;
        int p12 = p1 * (int)Math.pow(10, len2) + p2;
        int p21 = p2 * (int)Math.pow(10, len1) + p1;

        return Primes.isPrime(p12) && Primes.isPrime(p21);
    }
}
