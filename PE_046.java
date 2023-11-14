import UsefulFunctions.SieveOfPrimes;

public class PE_046 {
    public static void main(String[] args) {
        int limit = 100_000_000;

        findNumbers(limit);
    }

    private static void findNumbers(int limit) {
        boolean[] primes = SieveOfPrimes.sieveOfPrimes(limit);
        int[] primesArr = booleanArrToIntArr(primes);

        for (int i = 9; i < limit; i++) {
            if (!isOddComposite(i, primes)) continue;
            if (!canBeWrittenAsSumOfPrimeAndTwoSquares(i, primesArr)) System.out.println(i);
        }
    }

    private static boolean isOddComposite(int n, boolean[] primes) {
        return n % 2 == 1 && !primes[n];
    }

    private static int[] booleanArrToIntArr(boolean[] arr) {
        int counter = 0;
        for (boolean value : arr) if (value) counter++;

        int[] newArr = new int[counter];

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                newArr[index] = i;
                index++;
            }
        }

        return newArr;
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
