package euler;

import utils.Fraction;
import utils.Primes;

public class PE_0329 {
    private static boolean[] primes;
    private static boolean[] path;
    private static int size;
    private static Fraction<Long>[][] memo;

    static void main() {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static String PE() {
        String pathString = "PPPPNNPPPNPPNPN";
        int s = 500;
        prep(pathString, s);
        Fraction<Long> probability = pathProbability();
        return probability.num + "/" + probability.den;
    }

    @SuppressWarnings("unchecked")
    private static void prep(String pathString, int s) {
        path = path(pathString);
        size = s;
        memo = new Fraction[size+1][size+1];
        primes = Primes.sieve(size+1);
    }

    private static boolean[] path(String pathString) {
        boolean[] path = new boolean[pathString.length()];
        for (int i = 0; i < path.length; i++) {
            path[i] = pathString.charAt(i) == 'P';
        }
        return path;
    }

    private static Fraction<Long> pathProbability() {
        Fraction<Long> probability = new Fraction<>(0L);
        for (int i = 1; i <= size; i++) {
            probability = probability.add(pathProbability(i));
        }
        return probability.divide((long) size).simplify();
    }

    private static Fraction<Long> pathProbability(int start) {
        Fraction<Long> result = pathProbability(start, 0);
        return result.simplify();
    }

    private static Fraction<Long> pathProbability(int start, int i) {
        Fraction<Long> probability;
        if (primes[start] == path[i]) probability = new Fraction<>(2L, 3L);
        else probability = new Fraction<>(1L, 3L);
        if (i == path.length-1) return probability;
        if (memo[start][i] != null) return memo[start][i];

        if (start == 1) probability = probability.multiply(pathProbability(2, i+1));
        else if (start == size) probability = probability.multiply(pathProbability(size-1, i+1));
        else {
            Fraction<Long> p1 = pathProbability(start-1, i+1);
            Fraction<Long> p2 = pathProbability(start+1, i+1);
            probability = probability.multiply(new Fraction<>(1L, 2L)).multiply(p1.add(p2));
        }
        probability = probability.simplify();
        memo[start][i] = probability;
        return probability;
    }
}
