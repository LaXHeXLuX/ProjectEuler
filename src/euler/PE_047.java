package euler;

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
                if (!hasXDistinctPrimeFactors(i+j, m)) {
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

    private static boolean hasXDistinctPrimeFactors(long n, int x) {
        if (n % 2 == 0) {
            x--;
            do n /= 2;
            while (n % 2 == 0);
        }
        long limit = (long) Math.sqrt(n);
        for (int i = 3; i <= limit; i += 2) {
            if (n % i == 0) {
                x--;
                do n /= i;
                while (n % i == 0);
            }
        }

        if (n > 1) {
            x--;
        }

        return x == 0;
    }
}
