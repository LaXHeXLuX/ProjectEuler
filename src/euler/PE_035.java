package euler;

import utils.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_035 {
    private static boolean[] primes;
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        primes = Primes.sieveOfPrimes(limit);
        List<Integer> cyclicPrimes = findCyclicPrimesUnder(limit);
        return cyclicPrimes.size();
    }

    private static int[] generateCyclicNumbers(int n) {
        int[] cyclicNumbers = new int[(int) (Math.log10(n)+1)];
        cyclicNumbers[0] = n;

        for (int i = 1; i < cyclicNumbers.length; i++) {
            int lastDigit = n%10;
            n = n / 10 + lastDigit* (int) Math.pow(10, cyclicNumbers.length-1);
            cyclicNumbers[i] = n;
        }

        return cyclicNumbers;
    }

    private static boolean isCyclicPrime(int n, boolean[] primes) {
        int[] cyclicNumbers = generateCyclicNumbers(n);

        for (int cyclicNumber : cyclicNumbers) {
            if (!primes[cyclicNumber]) return false;
        }

        return true;
    }

    private static List<Integer> findCyclicPrimesUnder(int limit) {
        List<Integer> cyclicPrimes = new ArrayList<>();
        cyclicPrimes.add(2);

        for (int i = 3; i < limit; i+=2) {
            if (!primes[i]) continue;
            if (isCyclicPrime(i, primes)) cyclicPrimes.add(i);
        }

        return cyclicPrimes;
    }
}
