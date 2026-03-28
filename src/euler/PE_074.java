package euler;

import java.util.*;

public class PE_074 {
    private static final int[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    private static int[] digitFactorialChain;

    static void main() {
        System.out.println(PE());
    }

    public static String PE() {
        int n = 1_000_000;
        initDigitFactorialChain(n);
        int target = 60;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (chainLengthIs(i, target)) count++;
        }
        return String.valueOf(count);
    }

    private static void initDigitFactorialChain(int limit) {
        digitFactorialChain = new int[FACTORIALS[9]*((int) Math.log10(limit-1) + 1) + 1];
        digitFactorialChain[169] = 3;
        digitFactorialChain[363601] = 3;
        digitFactorialChain[1454] = 3;
        digitFactorialChain[871] = 2;
        digitFactorialChain[872] = 2;
        digitFactorialChain[45361] = 2;
        digitFactorialChain[45362] = 2;
        digitFactorialChain[1] = 1;
        digitFactorialChain[2] = 1;
        digitFactorialChain[145] = 1;
        digitFactorialChain[40585] = 1;
    }

    private static boolean chainLengthIs(int n, int target) {
        if (digitFactorialChain[n] > 0) return digitFactorialChain[n] == target;
        List<Integer> chain = new ArrayList<>();
        while (digitFactorialChain[n] == 0) {
            chain.add(n);
            n = sumOfDigitFactorials(n);
        }
        for (int i = 0; i < chain.size(); i++) {
            digitFactorialChain[chain.get(i)] = chain.size()-i + digitFactorialChain[n];
        }
        return digitFactorialChain[chain.getFirst()] == target;
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
