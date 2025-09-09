import util.Converter;
import util.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_035 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        int[] cyclicPrimes = findCyclicPrimesUnder(limit);

        return cyclicPrimes.length;
    }

    private static int[] generateCyclicNumbers(int n) {
        int[] cyclicNumbers = new int[(int) (Math.log10(n)+1)];
        cyclicNumbers[0] = n;

        for (int i = 1; i < cyclicNumbers.length; i++) {
            int lastDigit = n%10;
            n = (int) (n/10 + lastDigit*Math.pow(10, cyclicNumbers.length-1));
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

    private static int[] findCyclicPrimesUnder(int limit) {
        int primeLimit = (int) Math.pow(10, (int)Math.log10(1_250_062)+1);
        boolean[] primes = Primes.sieveOfPrimes(primeLimit);
        List<Integer> cyclicPrimes = new ArrayList<>();

        for (int i = 1; i < limit; i++) {
            if (isCyclicPrime(i, primes)) cyclicPrimes.add(i);
        }

        return Converter.listToArr(cyclicPrimes);
    }
}
