package euler;

import utils.Primes;

import java.util.HashMap;
import java.util.Map;

public class PE_005 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 20;
        return firstToDivide(arrOfNFirstElements(n));
    }

    private static long[] arrOfNFirstElements(int n) {
        long[] arr = new long[n];
        for (int i = 1; i < n+1; i++) {
            arr[i-1] = i;
        }
        return arr;
    }

    private static long firstToDivide(long[] numbers) {
        Primes.PF[][] allPrimeFactors = new Primes.PF[numbers.length][];
        for (int i = 0; i < numbers.length; i++) {
            allPrimeFactors[i] = Primes.primeFactors(numbers[i]);
        }
        Map<Long, Integer> biggestTerms = biggestTerms(allPrimeFactors);
        long product = 1;
        for (long key : biggestTerms.keySet()) {
            product *= (long) Math.pow(key, biggestTerms.get(key));
        }
        return product;
    }

    private static Map<Long, Integer> biggestTerms(Primes.PF[][] allTerms) {
        Map<Long, Integer> biggestTerms = new HashMap<>();
        for (Primes.PF[] pfs : allTerms) {
            for (Primes.PF pf : pfs) {
                if (!biggestTerms.containsKey(pf.primeFactor)) {
                    biggestTerms.put(pf.primeFactor, pf.power);
                } else if (biggestTerms.get(pf.primeFactor) < pf.power) {
                    biggestTerms.put(pf.primeFactor, pf.power);
                }
            }
        }
        return biggestTerms;
    }
}
