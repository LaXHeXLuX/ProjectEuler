package euler;

import java.util.*;

public class PE_095 {
    private static int[] divisorSums;
    private static boolean[] skip;

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int limit = 1_000_000;
        makeDivisorSums(limit);
        skip = new boolean[limit+1];
        Set<Integer> longestChain = longestChain();
        return Collections.min(longestChain);
    }

    private static void makeDivisorSums(int limit) {
        divisorSums = new int[limit+1];
        Arrays.fill(divisorSums, 1);
        for (int i = 2; i < divisorSums.length/2; i++) {
            int temp = i*2;
            while (temp < divisorSums.length) {
                divisorSums[temp] += i;
                temp += i;
            }
        }
    }

    private static Set<Integer> longestChain() {
        Set<Integer> longestChain = new HashSet<>();
        for (int i = 0; i < divisorSums.length; i++) {
            if (skip[i]) {
                continue;
            }
            Set<Integer> currentChain = chain(i);
            skip[i] = true;
            if (currentChain.size() > longestChain.size()) longestChain = currentChain;
        }
        return longestChain;
    }

    private static Set<Integer> chain(int n) {
        Set<Integer> chain = new HashSet<>();
        chain.add(n);
        int temp = divisorSums[n];
        while (temp < skip.length && !skip[temp]) {
            skip[temp] = true;
            if (temp == n) return chain;
            if (chain.contains(temp)) break;
            chain.add(temp);
            temp = divisorSums[temp];
        }
        return new HashSet<>();
    }
}
