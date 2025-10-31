package src.euler;

import util.Combinations;
import util.Converter;
import util.Primes;

import java.util.*;

public class PE_049 {

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 10_000;
        int inARow = 3;
        List<List<Integer>> unusualTerms = unusualTermsUnder(limit, inARow);
        for (List<Integer> unusualTerm : unusualTerms) {
            if (unusualTerm.getFirst() != 1487) return concat(unusualTerm, inARow);
        }
        return -1;
    }

    private static long concat(List<Integer> arr, int inARow) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < inARow; i++) {
            s.append(arr.getFirst() + arr.get(1)*i);
        }
        return Long.parseLong(s.toString());
    }

    private static List<List<Integer>> unusualTermsUnder(int limit, int inARow) {
        boolean[] primes = Primes.sieveOfPrimes(limit);
        List<List<Integer>> unusualTerms = new ArrayList<>();
        Set<Integer> skip = new HashSet<>();

        for (int i1 = limit/10; i1 < limit; i1++) {
            if (!primes[i1] || skip.contains(i1)) continue;
            int[] digits = Converter.digitArray(i1);
            int[][] perms = Combinations.permutations(digits);
            Set<Integer> primePermsSet = new HashSet<>();
            List<Integer> primePerms = new ArrayList<>();
            for (int[] perm : perms) {
                int permInt = Math.toIntExact(Converter.fromDigitArray(perm));
                if (permInt < limit/10 || !primes[permInt]) continue;
                primePerms.add(permInt);
                primePermsSet.add(permInt);
                skip.add(permInt);
            }
            addUnusualTerms(primePerms, primePermsSet, inARow, unusualTerms);
        }

        return unusualTerms;
    }

    private static void addUnusualTerms(List<Integer> perms, Set<Integer> permsSet, int inARow, List<List<Integer>> unusualTerms) {
        for (int i = 0; i < perms.size(); i++) {
            int p1 = perms.get(i);
            for (int j = i+1; j < perms.size(); j++) {
                int p2 = perms.get(j);
                int diff = p2 - p1;
                int temp = p2;
                boolean flag = true;
                for (int k = 0; k < inARow-2; k++) {
                    temp += diff;
                    if (!permsSet.contains(temp)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    unusualTerms.add(List.of(p1, diff));
                }
            }
        }
    }
}