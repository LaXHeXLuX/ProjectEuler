package euler;

import utils.Graph;
import utils.Primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PE_060 {
    private static boolean[] composites;
    private static final List<Integer> primesInt = new ArrayList<>();
    private static Set<Integer> lowestSumPrimeSet;
    private static int lowestSum = Integer.MAX_VALUE;
    private static final Graph primePairGraph = new Graph();

    public static void main(String[] args) {
        System.out.println(PE());
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
        int limit = 100_000_000;
        composites = new boolean[limit/2];
        composites[0] = true;
        for (int i = 3; i < Math.sqrt(limit); i+=2) {
            if (composites[i/2]) continue;
            int prod = i*i;
            while (prod < limit && prod > 0) {
                composites[prod/2] = true;
                prod += 2*i;
            }
            primesInt.add(i);
        }
        for (int prime : primesInt) {
            if (prime > lowestSum) return;
            String primeString = String.valueOf(prime);
            List<Integer> primePairSet = primePairSetFor(prime);
            primePairGraph.addNode(primeString);
            for (Integer i : primePairSet) {
                String node = String.valueOf(i);
                primePairGraph.addEdge(primeString, node, 1);
                primePairGraph.addEdge(node, primeString, 1);
            }
            Set<String> clique = primePairGraph.clique(n, primeString);
            if (!clique.isEmpty()) {
                Set<Integer> cliqueInt = clique.stream().map(Integer::parseInt).collect(Collectors.toSet());
                int sum = sum(cliqueInt);
                if (lowestSumPrimeSet == null || lowestSum > sum) {
                    lowestSumPrimeSet = cliqueInt;
                    lowestSum = sum;
                }
            }
        }
    }

    private static List<Integer> primePairSetFor(int p1) {
        List<Integer> primePairSet = new ArrayList<>();
        for (int p2 : primesInt) {
            if (p2 >= p1) break;
            if (isPrimePair(p1, p2)) primePairSet.add(p2);
        }
        return primePairSet;
    }

    private static boolean isPrimePair(int p1, int p2) {
        String p1s = String.valueOf(p1);
        String p2s = String.valueOf(p2);
        int p12 = Integer.parseInt(p1s + p2s);
        int p21 = Integer.parseInt(p2s + p1s);

        return isPrime(p12) && isPrime(p21);
    }

    private static boolean isPrime(int p) {
        if (p/2 < composites.length) return !composites[p/2];
        return Primes.isPrime(p);
    }
}
