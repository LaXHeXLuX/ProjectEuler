package euler;

import utils.Fraction;
import utils.Primes;

import java.util.HashMap;
import java.util.Map;

public class PE_329 {
    private static boolean[] primes;
    private static boolean[] path;
    private static int size;
    private static final Map<Integer, Map<Integer, Fraction<Long>>> memo = new HashMap<>();

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        String pathString = "PPPPNNPPPNPPNPN";
        path = path(pathString);
        size = 500;
        for (int i = 1; i <= size; i++) memo.put(i, new HashMap<>());
        primes = Primes.sieve(size+1);
        Fraction<Long> probability = pathProbability();
        return probability.num + "/" + probability.den;
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
        return probability.multiply(new Fraction<>(1L, (long) size)).simplify();
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
        if (memo.get(start).containsKey(i)) return memo.get(start).get(i);

        if (start == 1) probability = probability.multiply(pathProbability(2, i+1));
        else if (start == size) probability = probability.multiply(pathProbability(size-1, i+1));
        else {
            Fraction<Long> p1 = pathProbability(start-1, i+1);
            Fraction<Long> p2 = pathProbability(start+1, i+1);
            probability = probability.multiply(new Fraction<>(1L, 2L)).multiply(p1.add(p2));
        }
        probability = probability.simplify();
        memo.get(start).put(i, probability);
        return probability;
    }
}
