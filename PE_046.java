import UsefulFunctions.Converter;
import UsefulFunctions.Primes;

public class PE_046 {
    public static void main(String[] args) {
        int limit = 100_000_000;

        findNumbers(limit);
    }

    private static void findNumbers(int limit) {
        boolean[] primes = Primes.sieveOfPrimes(limit);
        int[] primesArr = Converter.booleanArrToIntArr(primes);

        for (int i = 9; i < limit; i++) {
            if (!isOddComposite(i, primes)) continue;
            if (!canBeWrittenAsSumOfPrimeAndTwoSquares(i, primesArr)) System.out.println(i);
        }
    }

    private static boolean isOddComposite(int n, boolean[] primes) {
        return n % 2 == 1 && !primes[n];
    }

    private static boolean canBeWrittenAsSumOfPrimeAndTwoSquares(int n, int[] primes) {
        for (int i = 0; i < primes.length && primes[i] < n; i++) {
            int prime = primes[i];

            int square = 1;
            while (prime + 2*square*square < n) square++;

            if (prime + 2*square*square == n) return true;
        }
        return false;
    }
}
