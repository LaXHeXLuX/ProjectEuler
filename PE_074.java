import java.util.*;

public class PE_074 {
    private static int[] digitFactorialSums;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int n = 1_000_000;
        digitFactorialSums = new int[10_000_000];
        Arrays.fill(digitFactorialSums, -1);
        int[] longest = longestChainLengthBelow(n);
        System.out.println(longest.length);

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end-start) + " ms");
    }

    private static int[] longestChainLengthBelow(int limit) {
        List<Integer> maxIList = new ArrayList<>();
        maxIList.add(1);
        int maxChainLength = getFactorialCycle(1).length-1;

        for (int i = 2; i < limit; i++) {
            int currentChainLength = getFactorialCycle(i).length-1;
            if (currentChainLength == maxChainLength) {
                maxIList.add(i);
            }
            else if (currentChainLength > maxChainLength) {
                maxChainLength = currentChainLength;
                maxIList = new ArrayList<>();
                maxIList.add(i);
            }
        }

        return Converter.listToArr(maxIList);
    }

    private static int[] getFactorialCycle(int n) {
        Map<Integer, Integer> factorialSumsMap = new HashMap<>();

        List<Integer> factorialSumsList = new ArrayList<>();
        factorialSumsList.add(n);

        int element = getSumOfDigitFactorials(n);
        if (element == n) return new int[] {};
        while (!factorialSumsMap.containsKey(element)) {
            factorialSumsMap.put(element, factorialSumsList.size());
            factorialSumsList.add(element);

            element = getSumOfDigitFactorials(element);
        }

        return Converter.listToArr(factorialSumsList);

    }

    private static int getSumOfDigitFactorials(int n) {
        if (digitFactorialSums[n] == -1) {
            int[] digits = Converter.digitArray(n);
            int sum = 0;
            for (int digit : digits) {
                sum += Combinations.factorial(digit);
            }
            digitFactorialSums[n] = sum;
            return sum;
        }
        return digitFactorialSums[n];
    }
}
