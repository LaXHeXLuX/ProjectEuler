package euler;

import utils.Primes;

public class PE_204 {
    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int type = 100;
        long limit = 1_000_000_000;
        return hammingNumbers(type, limit);
    }

    private static long hammingNumbers(int type, long limit) {
        int[] primes = Primes.primes(type+1);
        return hammingNumbersRec(primes, 0, 1, limit);
    }

    private static long hammingNumbersRec(int[] primes, int index, long p, long limit) {
        if (p > limit) return 0;
        long sum = 1;
        for (int i = index; i < primes.length; i++) {
            sum += hammingNumbersRec(primes, i, p*primes[i], limit);
        }
        return sum;
    }
}
