import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE5 {
    public static void main(String[] args) {
        System.out.println(firstToDivide(arrOfNFirstElements(100)));
    }
    private static long[] arrOfNFirstElements(int n) {
        long[] arr = new long[n];
        for (int i = 1; i < n+1; i++) {
            arr[i-1] = i;
        }
        return arr;
    }
    private static long firstToDivide(long[] numbers) {
        long[][] allPrimeFactors = new long[numbers.length][];
        for (int i = 0; i < numbers.length; i++) {
            allPrimeFactors[i] = findPrimeFactors(numbers[i]);
        }
        Map<Long, Long> biggestTerms = getBiggestTerms(allPrimeFactors);
        long product = 1;
        for (long key : biggestTerms.keySet()) {
            product *= Math.pow(key, biggestTerms.get(key));
        }
        return product;
    }
    private static Map<Long, Long> getBiggestTerms(long[][] allTerms) {
        Map<Long, Long> biggestTerms = new HashMap<>();
        for (long[] allTerm : allTerms) {
            Map<Long, Long> currentTerms = arrToMap(allTerm);
            for (Long key : currentTerms.keySet()) {
                if (!biggestTerms.containsKey(key)) {
                    biggestTerms.put(key, currentTerms.get(key));
                } else if (biggestTerms.get(key) < currentTerms.get(key)) {
                    biggestTerms.put(key, currentTerms.get(key));
                }
            }
        }
        return biggestTerms;
    }
    private static Map<Long, Long> arrToMap(long[] terms) {
        Map<Long, Long> refactoredTerms = new HashMap<>();
        for (long term : terms) {
            if (!refactoredTerms.containsKey(term)) refactoredTerms.put(term, 1L);
            else refactoredTerms.put(term, refactoredTerms.get(term)+1);
        }
        return refactoredTerms;
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
