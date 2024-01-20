public class PE_070 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int limit = 10_000_000;
        int[] allPrimes = Converter.booleanArrToIntArr(Primes.sieveOfPrimes(limit));
        System.out.println("Time to make all primes: " + (System.currentTimeMillis()-start) + " ms");
        int answer = (int) findNumberWithPropertyWithSmallestScore(limit, allPrimes);
        System.out.println(answer + " - " + Primes.eulersTotient(answer));

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end-start) + " ms");
    }

    private static boolean hasProperty(long n, long totient) {
        int[] primeDigits = Converter.digitArray(n);
        int[] numberDigits = Converter.digitArray(totient);
        return Combinations.isPermutationOf(primeDigits, numberDigits);
    }
    private static long findNumberWithPropertyWithSmallestScore(int limit, int[] primes) {
        int indexLimit = primes.length-1;
        int limitSqrt = (int) Math.sqrt(limit);
        while (primes[indexLimit] > limitSqrt) indexLimit--;

        for (int primeIndex1 = indexLimit; primeIndex1 >= 0; primeIndex1--) {
            int upperBound = Primes.upperBoundForNumberOfSmallerPrimes(limit/primes[primeIndex1]);
            if (upperBound >= primes.length) upperBound = primes.length-1;

            for (int primeIndex2 = upperBound; primeIndex2 >= primeIndex1; primeIndex2--) {
                long number = (long) primes[primeIndex1] * primes[primeIndex2];
                long totient = Primes.eulersTotient(number);
                if (hasProperty(number, totient)) return number;
            }
        }
        return -1;
    }
}
