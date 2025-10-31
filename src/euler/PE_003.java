package src.euler;

import util.Primes;

public class PE_003 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        long[] pf = Primes.findPrimeFactors(600851475143L);
        return pf[pf.length-1];
    }
}
