import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PE3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPrimeFactors(600851475143L)));
    }
    private static long[] findPrimeFactors(long n) {
        List<Long> primeFactors = new ArrayList<>();
        while (n > 1) {
            boolean nIsPrime = true;
            for (long i = 2; i < Math.pow(n, 0.5)+1; i++) {
                if (n % i == 0) {
                    n /= i;
                    primeFactors.add(i);
                    nIsPrime = false;
                    break;
                }
            }
            if (nIsPrime) {
                primeFactors.add(n);
                break;
            }
        }
        return listToArr(primeFactors);
    }
    private static long[] listToArr(List<Long> list) {
        long[] arr = new long[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
