import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE_037 {
    public static void main(String[] args) {
        long[] all = findAllTruncatablePrimes();
        System.out.println(Arrays.toString(all));

        long sum = 0;
        for (long a : all) sum += a;
        System.out.println(sum);
    }

    private static long[] getTruncations(long n) {
        int[] digits = Converter.digitArray(n);
        int[][] truncations = new int[digits.length*2-1][];

        for (int i = 0; i < digits.length; i++) {
            truncations[i] = new int[i+1];
            System.arraycopy(digits, 0, truncations[i], 0, truncations[i].length);
        }
        for (int i = 0; i < digits.length-1; i++) {
            int truncationIndex = digits.length+i;
            truncations[truncationIndex] = new int[digits.length-i-1];
            System.arraycopy(digits, i+1, truncations[truncationIndex], 0, truncations[truncationIndex].length);
        }

        long[] truncatedNumbers = new long[truncations.length];
        for (int i = 0; i < truncations.length; i++) {
            truncatedNumbers[i] = Converter.digitFromArrayLong(truncations[i]);
        }
        return truncatedNumbers;
    }

    private static boolean isTruncatablePrime(long n, boolean[] primes) {
        long[] truncations = getTruncations(n);
        for (long truncation : truncations) {
            if (!primes[(int) truncation]) return false;
        }
        return true;
    }

    private static long[] findAllTruncatablePrimes() {
        int limit = 1_000_000;
        boolean[] primes = Primes.sieveOfPrimes(limit);
        List<Long> truncatables = new ArrayList<>();

        for (long i = 10; i < limit-1; i++) {
            if (isTruncatablePrime(i, primes)) {
                truncatables.add(i);
            }
        }

        return Converter.listToArr(truncatables);
    }
}
