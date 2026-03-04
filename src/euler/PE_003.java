package euler;

import utils.Primes;

public class PE_003 {
    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        Primes.PF[] pf = Primes.primeFactors(600851475143L);
        return String.valueOf(pf[pf.length-1].primeFactor);
    }
}
