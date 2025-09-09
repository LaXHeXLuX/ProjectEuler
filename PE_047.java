import util.Primes;

public class PE_047 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long[] result = firstNConsecutiveNumbersToHaveMDistinctPrimeFactors(4, 4);
        return result[0];
    }

    public static long[] firstNConsecutiveNumbersToHaveMDistinctPrimeFactors(int n, int m) {
        long i = 2;
        boolean solved = false;

        while (!solved) {
            if (i < 0) return new long[0];
            solved = true;
            for (int j = 0; j < n; j++) {
                if (countDistinctPrimeFactors(i+j) != m) {

                    solved = false;
                    break;
                }

            }
            i++;
        }
        i--;

        long[] answer = new long[n];

        for (int j = 0; j < n; j++) answer[j] = i+j;

        return answer;
    }

    public static int countDistinctPrimeFactors(long n) {
        long[] primeFactors = Primes.findPrimeFactors(n);
        int distinctCount = 1;

        for (int i = 1; i < primeFactors.length; i++) {
            if (primeFactors[i] != primeFactors[i-1]) distinctCount++;
        }

        return distinctCount;
    }
}
