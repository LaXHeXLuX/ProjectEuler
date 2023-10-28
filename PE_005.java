import UsefulFunctions.PrimeFactors;

import java.util.HashMap;
import java.util.Map;

public class PE_005 {
    public static void main(String[] args) {
        int n = 100;
        System.out.println(firstToDivide(arrOfNFirstElements(n)));
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
        PrimeFactors pf = new PrimeFactors();
        for (int i = 0; i < numbers.length; i++) {
            allPrimeFactors[i] = pf.findPrimeFactors(numbers[i]);
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
}
