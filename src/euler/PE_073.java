package euler;

import java.util.ArrayList;
import java.util.List;

public class PE_073 {
    private static final List<List<Integer>> primeFactors = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int[] fraction1 = {1, 3};
        int[] fraction2 = {1, 2};
        int limit = 12_000;
        makePrimeFactors(limit+1);
        return numberOfFractionsBetween(fraction1, fraction2, limit);
    }

    private static void makePrimeFactors(int limit) {
        for (int i = 0; i < limit; i++) {
            primeFactors.add(new ArrayList<>());
        }
        for (int i = 2; i < limit; i += 2) {
            primeFactors.get(i).add(2);
        }
        for (int i = 3; i <= primeFactors.size() / 2; i+=2) {
            if (!primeFactors.get(i).isEmpty()) continue;
            for (int j = i; j < limit; j += i) {
                primeFactors.get(j).add(i);
            }
        }
        for (int i = primeFactors.size() / 2 + 1; i < primeFactors.size(); i++) {
            if (primeFactors.get(i).isEmpty()) primeFactors.get(i).add(i);
        }
    }

    private static long numberOfFractionsBetween(int[] fraction1, int[] fraction2, int limit) {
        long count = 0;
        for (int i = 2; i <= limit; i++) {
            int start = i * fraction1[0] / fraction1[1] + 1;
            int end = (i-1) * fraction2[0] / fraction2[1];
            count += countCoprimeUpToLimit(primeFactors.get(i), end) - countCoprimeUpToLimit(primeFactors.get(i), start-1);
        }
        return count;
    }

    private static int countCoprimeUpToLimit(List<Integer> primes, int limit) {
        int k = primes.size();
        int count = 0;
        int subsets = 1 << k;

        for (int mask = 0; mask < subsets; mask++) {
            int product = 1;
            int sign = 1;
            for (int j = 0; j < k; j++) {
                if ((mask & (1 << j)) != 0) {
                    product *= primes.get(j);
                    sign *= -1;
                }
            }
            if (product > limit) continue;
            count += sign * (limit / product);
        }
        return count;
    }
}
