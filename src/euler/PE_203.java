package euler;

import utils.Primes;

import java.util.HashSet;
import java.util.Set;

public class PE_203 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int rows = 51;
        Set<Long> distinctPascalNumbers = distinctPascalNumbers(rows);
        return sumOfSquareFreeElements(distinctPascalNumbers);
    }

    private static long sumOfSquareFreeElements(Set<Long> set) {
        long sum = 0;
        for (Long i : set) {
            Primes.PF[] pfs = Primes.primeFactors(i);
            boolean square = false;
            for (Primes.PF pf : pfs) {
                if (pf.power > 1) {
                    square = true;
                    break;
                }
            }
            if (!square) sum += i;
        }
        return sum;
    }

    private static Set<Long> distinctPascalNumbers(int rows) {
        long[][] triangle = new long[rows][];
        triangle[0] = new long[] {1};
        Set<Long> distinctPascalNumbers = new HashSet<>();
        distinctPascalNumbers.add(1L);
        for (int i = 1; i < rows; i++) {
            triangle[i] = new long[i+1];
            triangle[i][0] = 1;
            triangle[i][triangle[i].length-1] = 1;
            for (int j = 1; j < triangle[i].length-1; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
                distinctPascalNumbers.add(triangle[i][j]);
            }
        }
        return distinctPascalNumbers;
    }
}
