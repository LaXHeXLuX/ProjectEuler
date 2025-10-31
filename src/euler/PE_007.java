package euler;

import utils.Primes;

public class PE_007 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        return Primes.nthPrime(10001);
    }
}
