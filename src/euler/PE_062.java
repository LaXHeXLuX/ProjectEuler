package euler;

import utils.ArrayFunctions;
import utils.Converter;

import java.util.*;

public class PE_062 {
    private static final Map<String, Set<Long>> powerPermutations = new HashMap<>();

    static void main() {
        System.out.println(PE());
    }

    public static long PE() {
        int power = 3;
        int count = 5;
        Set<Long> result = powerPermutations(power, count);
        long min = Long.MAX_VALUE;
        for (Long el : result) {
            if (el < min) min = el;
        }
        return min;
    }

    private static String digitArrayToString(int[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i : arr) {
            s.append(i);
        }
        return s.toString();
    }

    private static Set<Long> lowestCandidate(Set<String> candidates) {
        long lowest = Long.MAX_VALUE;
        Set<Long> lowestCandidate = new HashSet<>();
        for (String candidate : candidates) {
            Set<Long> perms = powerPermutations.get(candidate);
            for (Long perm : perms) {
                if (perm < lowest) {
                    lowest = perm;
                    lowestCandidate = perms;
                }
            }
        }
        return lowestCandidate;
    }

    private static Set<Long> powerPermutations(int power, int count) {
        Set<String> candidates = new HashSet<>();
        int n = 1;
        int nextThreshold = 10;
        while (true) {
            long p = (long) Math.pow(n, power);
            if (p > nextThreshold) {
                if (!candidates.isEmpty()) {
                    return lowestCandidate(candidates);
                }
                nextThreshold *= 10;
            }
            String firstPermutation = digitArrayToString(ArrayFunctions.mergeSort(Converter.digitArray(p)));
            if (!powerPermutations.containsKey(firstPermutation))
                powerPermutations.put(firstPermutation, new HashSet<>());
            Set<Long> permutation = powerPermutations.get(firstPermutation);
            permutation.add(p);
            if (permutation.size() == count) {
                candidates.add(firstPermutation);
            }
            else if (permutation.size() > count) {
                candidates.remove(firstPermutation);
            }

            n++;
        }
    }
}