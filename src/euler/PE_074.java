package src.euler;

import java.util.*;

public class PE_074 {
    private static Map<Integer, List<Integer>> digitFactorialChain;
    private static int[] digitFactorialSums;
    private static final int[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        int n = 1_000_000;
        digitFactorialChain = new HashMap<>();
        digitFactorialSums = new int[5_000_000];
        Arrays.fill(digitFactorialSums, -1);
        List<Integer> longest = longestChainLengths(n);
        return longest.size();
    }

    private static List<Integer> longestChainLengths(int limit) {
        List<Integer> maxList = new ArrayList<>();
        maxList.add(1);
        int maxChainLength = 1;

        for (int i = 2; i < limit; i++) {
            List<Integer> chain;
            if (!digitFactorialChain.containsKey(i)) {
                chain = getFactorialCycle(i);
                digitFactorialChain.put(i, chain);
            }
            chain = digitFactorialChain.get(i);
            if (chain.size() == maxChainLength) {
                maxList.add(i);
            }
            else if (chain.size() > maxChainLength) {
                maxChainLength = chain.size();
                maxList = new ArrayList<>();
                maxList.add(i);
            }
        }
        return maxList;
    }

    private static List<Integer> getFactorialCycle(int n) {
        Map<Integer, Integer> factorialSumsMap = new HashMap<>();

        List<Integer> factorialSumsList = new ArrayList<>();
        factorialSumsList.add(n);

        factorialSumsMap.put(n, factorialSumsList.size());
        int last = -1;
        int element = getSumOfDigitFactorials(n);
        while (!factorialSumsMap.containsKey(element)) {
            if (digitFactorialChain.containsKey(element)) {
                List<Integer> chain = digitFactorialChain.get(element);
                int endPoint = Collections.binarySearch(chain, last);
                if (endPoint < 0) {
                    factorialSumsList.addAll(chain);
                    return factorialSumsList;
                }
            }
            factorialSumsMap.put(element, factorialSumsList.size());
            factorialSumsList.add(element);

            last = element;
            element = getSumOfDigitFactorials(element);
        }

        return factorialSumsList;

    }

    private static int getSumOfDigitFactorials(int n) {
        if (digitFactorialSums[n] == -1) {
            int sum = 0;
            while (n > 9) {
                sum += FACTORIALS[n % 10];
                n /= 10;
            }
            sum += FACTORIALS[n];
            digitFactorialSums[n] = sum;
        }
        return digitFactorialSums[n];
    }
}
