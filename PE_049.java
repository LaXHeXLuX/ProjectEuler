import util.Combinations;
import util.Converter;
import util.Primes;

import java.util.ArrayList;
import java.util.List;

public class PE_049 {
    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000;
        int[][] unusualTerms = findUnusualTermsUnder(limit);
        for (int[] unusualTerm : unusualTerms) {
            if (unusualTerm[0] != 1487) return concatenate(unusualTerm);
        }
        return -1;
    }

    private static long concatenate(int[] arr) {
        StringBuilder n = new StringBuilder();
        for (int i : arr) {
            n.append(i);
        }
        return Long.parseLong(n.toString());
    }

    private static int[][] findUnusualTermsUnder(int limit) {
        int inARow = 3;
        boolean[] primes = Primes.sieveOfPrimes(limit);
        List<int[]> unusualTerms = new ArrayList<>();

        for (int startingNumber = 1; startingNumber < limit; startingNumber++) {
            int[] validAdders = findAddersToMakeNUnusual(startingNumber, primes, inARow);
            if (validAdders.length == 0) continue;
            addUnusualTerms(startingNumber, validAdders, inARow, unusualTerms);
        }

        return Converter.listToArr(unusualTerms, int[].class);
    }

    private static void addUnusualTerms(int startingNumber, int[] adders, int inARow, List<int[]> unusualTerms) {
        for (int adder : adders) {
            int[] unusualTerm = new int[inARow];

            for (int i = 0; i < inARow; i++) {
                unusualTerm[i] = startingNumber + adder*i;
            }

            unusualTerms.add(unusualTerm);
        }
    }

    private static int[] findAddersToMakeNUnusual(int n, boolean[] primes, int inARow) {
        if (!primes[n]) return new int[0];
        int limit = (int) Math.pow(10, (int)Math.log10(n)+1);
        int adderLimit = (limit-n) / (inARow-1);
        List<Integer> adders = new ArrayList<>();

        for (int adder = 2; adder < adderLimit; adder+=2) {
            if (isUnusual(n, adder, primes, inARow)) adders.add(adder);
        }

        return Converter.listToArr(adders, Integer.class);
    }

    private static boolean isUnusual(int n, int adder, boolean[] primes, int inARow) {
        int originalN = n;
        for (int i = 0; i < inARow-1; i++) {
            n += adder;
            if (!primes[n]) return false;
            int[] originalDigits = Converter.digitArray(originalN);
            int[] nDigits = Converter.digitArray(n);
            if (!Combinations.isPermutation(originalDigits, nDigits)) return false;
        }

        return true;
    }
}
