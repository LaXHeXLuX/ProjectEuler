import util.Converter;
import util.LongFraction;
import util.Primes;

import java.util.*;

public class PE_070 {
    private static boolean[] primes;
    private static int[] smallPrimes;

    public static void main(String[] args) {
        double s = System.currentTimeMillis();
        System.out.println(PE());
        double e = System.currentTimeMillis();
        System.out.println((e-s) + " ms");
    }

    public static long PE() {
        int limit = 10_000_000;
        primes = Primes.sieveOfPrimes(limit);
        smallPrimes = Converter.booleanArrToIntArr(Primes.sieveOfPrimes(100));
        return findNumberWithPropertyWithSmallestScore();
    }

    private static int findNumberWithPropertyWithSmallestScore() {
        LongFraction smallestScore = new LongFraction(-1, 1);
        int smallestN = -1;
        for (int i = primes.length-1; i > 1; i-=2) {
            boolean skip = false;
            for (int smallPrime : smallPrimes) {
                if (i % smallPrime == 0) {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;
            int totient;
            LongFraction score;
            if (primes[i]) {
                totient = i-1;
                if (smallestScore.numerator >= 0 && smallestScore.compareTo(new LongFraction(i, totient)) < 0) {
                    return smallestN;
                }
                continue;
            }
            totient = Math.toIntExact(Primes.eulersTotient(i));
            if (!hasProperty(i, totient)) continue;
            score = new LongFraction(i, totient);
            if (smallestScore.numerator < 0 || score.compareTo(smallestScore) < 0) {
                smallestN = i;
                smallestScore = score;
            }
        }

        return smallestN;
    }

    private static boolean hasProperty(long n, long totient) {
        Map<Integer, Integer> digits = new HashMap<>();
        while (n > 0) {
            int digit = Math.toIntExact(n % 10);
            digits.put(digit, digits.getOrDefault(digit, 0) + 1);
            n /= 10;
        }
        while (totient > 0) {
            int digit = Math.toIntExact(totient % 10);
            int current = digits.getOrDefault(digit, 0);
            if (current == 0) return  false;
            if (current == 1) digits.remove(digit);
            else digits.put(digit, current-1);

            totient /= 10;
        }

        return digits.isEmpty();
    }
}
