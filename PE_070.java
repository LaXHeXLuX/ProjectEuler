import util.Combinations;
import util.Converter;
import util.LongFraction;
import util.Primes;

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
        int[] primeDigits = Converter.digitArray(n);
        int[] numberDigits = Converter.digitArray(totient);
        return Combinations.isPermutation(primeDigits, numberDigits);
    }
}
