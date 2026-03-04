package euler;

import utils.Primes;

public class PE_007 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        return String.valueOf(Primes.nthPrime(10001));
    }
}
