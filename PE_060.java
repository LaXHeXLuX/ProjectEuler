import util.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class PE_060 {
    private static boolean[] primes;
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
        primes = new boolean[limit];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i < Math.sqrt(limit); i++) {
            if (!primes[i]) continue;
            int prod = i*i;
            while (prod < limit && prod > 0) {
                primes[prod] = false;
                prod += i;
            }
            primesInt.add(i);
        }
        for (int prime : primesInt) {
            if (prime > lowestSum) break;
            String primeString = String.valueOf(prime);
            Set<Integer> primePairSet = primePairSetFor(prime);
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
        int p12 = Integer.parseInt(p1s + p2s);
        int p21 = Integer.parseInt(p2s + p1s);

        return primes[p12] && primes[p21];
    }
}
