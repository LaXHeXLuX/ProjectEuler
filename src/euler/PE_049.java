package euler;

import utils.Combinations;
import utils.Primes;

import java.util.Arrays;

public class PE_049 {

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int exclude = 1487;
        int[] unusualTerm = otherUnusualTerm(exclude);
        assert unusualTerm != null;
        int p1 = unusualTerm[0];
        int d = unusualTerm[1];
        return String.valueOf(p1) + (p1 + d) + (p1 + 2*d);
    }

    private static int[] otherUnusualTerm(int exclude) {
        int limit = 10_000;
        boolean[] composites = Primes.compositeSieve(limit);
        int[] primes = Primes.primes(composites);

        for (int i = 1; i < primes.length; i++) {
            if (primes[i] == exclude) continue;
            int jLimit = Arrays.binarySearch(primes, (limit + primes[i]) >> 1);
            if (jLimit < 0) jLimit = -jLimit - 1;
            if (jLimit > primes.length) jLimit = primes.length;
            for (int j = i+1; j < jLimit; j++) {
                int d = primes[j] - primes[i];
                if (composites[(primes[j] + d) >> 1]) continue;
                if (!Combinations.isPermutation(primes[i], primes[j])) continue;
                if (Combinations.isPermutation(primes[i], primes[j] + d)) {
                    return new int[] {primes[i], d};
                }
            }
        }

        return null;
    }
}