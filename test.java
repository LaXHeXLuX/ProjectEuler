import UsefulFunctions.Divisors;
import UsefulFunctions.PrimeFactors;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Divisors d = new Divisors();
        PrimeFactors pf = new PrimeFactors();
        //System.out.println(Arrays.toString(pf.findPrimeFactors(4)));
        int mostDivisors = 0;
        for (int i = 1; i < 100_000; i++) {
            long[] divisors = d.divisors(i);
            if (divisors.length > mostDivisors) {
                mostDivisors = divisors.length;
                System.out.println(i + ": " + divisors.length + " - " + Arrays.toString(pf.findPrimeFactors(i)));
            }
        }
    }
}
