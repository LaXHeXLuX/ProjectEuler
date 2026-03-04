package euler;

import utils.Primes;

public class PE_010 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 2_000_000;
        return String.valueOf(sumOfAllFalseElements(Primes.compositeSieve(n)));
    }

    private static long sumOfAllFalseElements(boolean[] arr) {
        long sum = 2;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) sum += ((long) i << 1) | 1;
        }
        return sum;
    }
}
