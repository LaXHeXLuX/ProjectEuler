package euler;

import utils.Primes;

public class PE_003 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        Primes.PF[] pf = Primes.primeFactors(600851475143L);
        return pf[pf.length-1].primeFactor;
    }
}
