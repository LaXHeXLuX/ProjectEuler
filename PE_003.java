import UsefulFunctions.PrimeFactors;

import java.util.Arrays;

public class PE_003 {
    public static void main(String[] args) {
        PrimeFactors pf = new PrimeFactors();
        System.out.println(Arrays.toString(pf.findPrimeFactors(600851475143L)));
    }
}
