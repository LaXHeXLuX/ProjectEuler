package euler;

import java.util.*;

public class PE_074 {
    private static final Map<Integer, Integer> digitFactorialChain = new HashMap<>() {{
        put(169, 3);
        put(363601, 3);
        put(1454, 3);
        put(871, 2);
        put(872, 2);
        put(45361, 2);
        put(45362, 2);
        put(1, 1);
        put(2, 1);
        put(145, 1);
        put(40585, 1);
    }};
    private static final int[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 1_000_000;
        List<Integer> longest = longestChainLengths(n);
        return longest.size();
    }

    private static List<Integer> longestChainLengths(int limit) {
        List<Integer> maxList = new ArrayList<>();
        maxList.add(1);
        int maxChainLength = 1;

        for (int i = 2; i < limit; i++) {
            int chain = chainLength(i);
            if (chain == maxChainLength) {
                maxList.add(i);
            }
            else if (chain > maxChainLength) {
                maxChainLength = chain;
                maxList = new ArrayList<>();
                maxList.add(i);
            }
        }
        return maxList;
    }

    private static int chainLength(int n) {
        if (digitFactorialChain.containsKey(n)) return digitFactorialChain.get(n);
        List<Integer> chain = new ArrayList<>();
        while (!digitFactorialChain.containsKey(n)) {
            chain.add(n);
            n = sumOfDigitFactorials(n);
        }
        for (int i = 0; i < chain.size(); i++) {
            digitFactorialChain.put(chain.get(i), chain.size()-i + digitFactorialChain.get(n));
        }
        return digitFactorialChain.get(chain.getFirst());
    }

    private static int sumOfDigitFactorials(int n) {
        int sum = 0;
        int temp = n;
        while (temp > 9) {
            sum += FACTORIALS[temp % 10];
            temp /= 10;
        }
        sum += FACTORIALS[temp];
        return sum;
    }
}
